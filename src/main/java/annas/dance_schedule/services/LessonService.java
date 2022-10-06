package annas.dance_schedule.services;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final CarnetRepository carnetRepository;
    private final CarnetService carnetService;
    private final Logger logger = Logger.getLogger("LessonService");

    public LessonService(LessonRepository lessonRepository, UserRepository userRepository, CarnetRepository carnetRepository, CarnetService carnetService) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.carnetRepository = carnetRepository;
        this.carnetService = carnetService;
    }

    @Transactional
    public void UpdateLessonsStatus() {
        List<Lesson> allLessons = lessonRepository.findAll();
        List<Lesson> activeLessons = allLessons.stream()
                .filter(this::LessonBegins)
                .filter(lesson -> lesson.getState().equals("active"))
                .collect(Collectors.toList());
        for (Lesson lesson : activeLessons) {
            List<User> lessonParticipants = lesson.getParticipants();

            if (lessonParticipants.size() > lesson.getSlots()) {
                for (int i = lessonParticipants.size(); i > lesson.getSlots(); i--) {
                    User user = lessonParticipants.get(i - 1);
                    Carnet userCarnet = carnetRepository.findAllByUserAndExpireDateAfter(user, lesson.getBeginTime().toLocalDate())
                            .stream()
                            .filter(carnet -> carnet.getAccessNumber() >= lesson.getAccessNumber())
                            .findFirst().get(); //użytkownik zapisany na dane zajęcia musi mieć jakiś aktywny karnet
                    userCarnet.setEntrances(userCarnet.getEntrances() + 1);
                    carnetService.update(userCarnet);
                    lessonRepository.deleteParticipant(user.getId(), lesson.getId());

                    logger.log(Level.INFO, " zwrócono jedno wejście na zajęcia i wypisano z listy rezerwowej: " + user.getEmail());
                }
            }
        }
        activeLessons.forEach(lesson -> lesson.setState("started"));
        activeLessons.forEach(this::update);
        logger.log(Level.INFO, "Zaktualizowano status nadchodzących zajęć w ilości  " + activeLessons.size());
    }

    public boolean LessonBegins(Lesson lesson) {
        return lesson.getBeginTime().isBefore(LocalDateTime.now().plusHours(2));
    }

    @Transactional
    public void update(Lesson lesson) {
        Optional<Lesson> oldLesson = lessonRepository.findById(lesson.getId());
        if (oldLesson.isPresent()) {
            lessonRepository.update(
                    lesson.getName(),
                    lesson.getBeginTime(),
                    lesson.getState(),
                    lesson.getSlots(),
                    lesson.getAccessNumber(),
                    lesson.getLevel(),
                    lesson.getPlace(),
                    lesson.getTrainer(),
                    lesson.getId()
            );
        }
    }

    @Transactional
    public void signUpForLesson(String userEmail, Long lessonId) {
        if (userRepository.findByEmail(userEmail).isPresent()
                && lessonRepository.findById(lessonId).isPresent()) {
            User user = userRepository.findByEmail(userEmail).get();
            Lesson lesson = lessonRepository.findById(lessonId).get();
            List<User> participants = lesson.getParticipants();
            participants.add(user);
            lesson.setParticipants(participants);
            addNewParticipantsToLesson(lesson);

        }
    }

    @Transactional
    public void addNewParticipantsToLesson(Lesson lesson) {
        Optional<Lesson> oldLesson = lessonRepository.findById(lesson.getId());
        if (oldLesson.isPresent()) {
            List<User> participants = lesson.getParticipants();

            for (User user : participants) {
                if (!lessonRepository.findLessonsByParticipantsIsContaining(user).contains(lesson)) {
                    lessonRepository.insertParticipant(user.getId(), lesson.getId());
                }

            }

        }
    }

    public List<Lesson> getLessonByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59);
        List<Lesson> todayLessons = lessonRepository.findLessonsByBeginTimeBetween(startOfDay, endOfDay);
        todayLessons.sort(Comparator.comparing(Lesson::getBeginTime));
        return todayLessons;
    }
    public void saveLesson (Lesson lesson) {
        lessonRepository.saveAndFlush(lesson);
    }


}
