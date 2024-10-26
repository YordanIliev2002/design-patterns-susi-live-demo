# During the lecture

## Models
We started by implementing the models - `Student`, `Teacher`, `Course`.  
Because the relationship between `Student` and `Course` is many-to-many, we created an additional model called `Enrollment` to represent it.  
We used Java 17 records, because they are a quick and simple way to create data objects.  

## Teacher
We decided to start with the `Teacher` creation functionality. We had to persist `Teacher` instances somewhere. (in memory for now).  

In order to decouple our "business logic" code from the persistence, we decided to create an interface `TeacherRepository`, which would represent the persistence operations for the `Teacher`, without exposing anything about where the data is actually stored (in-memory, file, relational DB, noSQL DB, cache, ...). This way we have *decoupled* the business logic from the persistence logic.

In order to illustrate the decoupling, we decided to implement two separate classes, which implement the interface - `HashMapTeacherRepository` and `ListTeacherRepository`.  

Then we created a `TeacherService`, which contains the logic for creating and searching teachers. In the future, it can contain the logic for updating teachers too.  

## Other models
For the other models, we created the same structure - `<Model>Repository` and `<Model>Service`.

### Discussion 1 (package structure)
We had a discussion about how to structure the packages with those ~12 classes.  
The two main contenders were:  
```yaml
susi:
  model:
    - Student
    - Teacher
    - ...
  repository:
    - TeacherRepository
    - ...
    impl:
      - HashMapTeacherRepository
      - ListTeacherRepository
      - ...
  service:
    - TeacherService
    - ...
```
or
```yaml
susi:
  teacher:
    - Teacher
    - TeacherRepository
    - HashMapTeacherRepository
    - ListTeacherRepository
    - TeacherService
  student:
    - ...
  enrollment:
    - ...
  courses:
    - ...
```
This is a common design decision in software development, and there are pros and cons to both approaches. There are countless discussions on reddit and stack overflow.  

I personally endorse the second one, because your future task at work is more likely to be "Add X to Courses" or "Add the ability for Teachers to do Y". This way you can easily find all related classes in one place - the `courses` or `teachers` packages.  
Nevertheless - we voted during the lecture, and decided to use the 1st approach for our *top-level* package splitting technique.  

In the end, we used the second approach for the subpackages in the `repository` package, because there were a lot of classes there.

### Discussion 2 (data-layer and service-layer coupling)
When creating a `Course` in the `CourseService`, we needed to validate, that the `teacherId` is valid.  
The two suggestions were to do it via the `TeacherService` or `TeacherRepository`.  

The correct answer is to do it in the `TeacherService`, because the service is responsible for the business logic, while the repository is responsible for data access.  
Some pros:
- The `TeacherService` should be the go-to class for all operations related to `Teacher` - it should be the only class that exposes the `Teacher` functionality to the outside world. The `TeacherRepository` is only a "tool" that is used by `TeacherService` to access/store the data.
- The `TeacherService` could do preliminary checks before accessing the data store - e.g. run the id through a regex, and only then call the repository, if absolutely necessary.
- The `TeacherService` can handle exceptions and errors in a more meaningful way, as it has the context of the business logic.
- The `TeacherService` can also implement caching or other optimizations that are related to the teacher ids.

As a rule of thumb(any rules should be taken with a pinch of salt ;) ) - "only the `<ModelX>Service` should access the `<ModelX>Repository`".  
Otherwise, you vastly increase the coupling, and you can also fuck up some of the system invariants, that the `<ModelX>Service` could try to impose.  
