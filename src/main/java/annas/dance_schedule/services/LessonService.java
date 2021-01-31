package annas.dance_schedule.services;

import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.LessonRepository;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger("LessonService");

    public LessonService(LessonRepository lessonRepository, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }


    public void UpdateLessonsStatus(){
        List<Lesson> futureLessons = lessonRepository.findLessonsByBeginTimeAfter(LocalDateTime.now());
        List<Lesson> activeLessonsApproaching = futureLessons.stream()
                    .filter(this::LessonBegins)
                    .filter(lesson -> lesson.getState().equals("active"))
                    .collect(Collectors.toList());

            activeLessonsApproaching.forEach(lesson -> lesson.setState("started"));
            activeLessonsApproaching.forEach(this::update);
            logger.log(Level.INFO, "Zaktualizowano status nadchodzących zajęć w ilości  " + activeLessonsApproaching.size());

    }

    public boolean LessonBegins(Lesson lesson){
        //jeśli dzień rozpoczęcia lekcji jest dzisiaj i godzina rozpoczęcia lekcji następuje wśród najbliższych 2 godzin
        return lesson.getBeginTime().getDayOfYear() == LocalDateTime.now().getDayOfYear()
                && lesson.getBeginTime().getHour() <= LocalDateTime.now().getHour() + 2;
    }
    @Transactional
    public void update(Lesson lesson) {
        Optional<Lesson> oldLesson = lessonRepository.findById(lesson.getId());
        if(oldLesson.isPresent()){
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
        } else lessonRepository.save(lesson);
    }
    @Transactional
    public void signUpForLesson(String userEmail, Long lessonId) {
        if (userRepository.findByEmail(userEmail).isPresent()
            && lessonRepository.findById(lessonId).isPresent()) {
            User user = userRepository.findByEmail(userEmail).get();
            List <Lesson> userLessons = user.getClassesParticipating();
            userLessons.add(lessonRepository.findById(lessonId).get());
            userRepository.updateUserLessons(userLessons, user.getId());
        }

    }

}
