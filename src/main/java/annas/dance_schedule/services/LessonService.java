package annas.dance_schedule.services;

import annas.dance_schedule.model.Lesson;
import annas.dance_schedule.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }


    public void UpdateLessonsStatus(){
        List<Lesson> futureLessons = lessonRepository.findLessonsByBeginTimeAfter(LocalDateTime.now());
        List<Lesson> activeLessonsApproaching = futureLessons.stream()
                .filter(this::LessonBegins)
                .filter(lesson -> lesson.getState().equals("active"))
                .collect(Collectors.toList());
        activeLessonsApproaching.forEach(lesson -> lesson.setState("starting"));
        activeLessonsApproaching.forEach(this::update);
    }

    public boolean LessonBegins(Lesson lesson){
        //jeśli dzień rozpoczęcia lekcji jest dzisiaj i godzina rozpoczęcia lekcji następuje wśród najbliższej godziny
        if(lesson.getBeginTime().getDayOfYear() == LocalDateTime.now().getDayOfYear()
                && lesson.getBeginTime().getHour() <= LocalDateTime.now().getHour()+1) {
            return true;
        } else return false;
    }
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


}
