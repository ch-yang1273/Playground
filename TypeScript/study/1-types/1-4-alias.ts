{
  /**
   * Type Alias
   */
  type Text = string;
  const name: Text = '이름';
  const address: Text = '한국';
  type Student = {
    name: string;
    age: number;
  }

  const student: Student = {
    name: '이름',
    age: 15,
  }

  /**
   * String Literal Type
   */
  type Name = 'name';
  let myName: Name = 'name'; // (O)
  // myName = 'others'; // (X)
}