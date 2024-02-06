{
  /**
   * Intersection Types: And
   */

  type Student = {
    name: string;
    score: number;
  }
  type Worker = {
    employeeId: number;
    work: () => void;
  }

  function internWork(person: Student & Worker) {
    console.log(person.name, person.score, person. employeeId, person.work());
  }
  internWork({
    name: '이름',
    score: 4,
    employeeId: 123,
    work: () => {},
  });
}