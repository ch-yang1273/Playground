import ExcelJS from 'exceljs';
async function createExcel() {
    try {
        // 새로운 워크북 생성
        const workbook = new ExcelJS.Workbook();
        // 새로운 워크시트 생성
        const worksheet = workbook.addWorksheet('테스트 시트');
        // 헤더 설정
        worksheet.columns = [
            { header: 'ID', key: 'id', width: 10 },
            { header: '이름', key: 'name', width: 20 },
            { header: '나이', key: 'age', width: 10 }
        ];
        // 데이터 추가
        const people = [
            { id: 1, name: '홍길동', age: 30 },
            { id: 2, name: '김철수', age: 25 },
            { id: 3, name: '이순신', age: 45 }
        ];
        people.forEach(person => worksheet.addRow(person));
        // 스타일 적용
        worksheet.getRow(1).font = { bold: true };
        worksheet.getRow(1).fill = { type: 'pattern', pattern: 'solid', fgColor: { argb: 'FFD700' } };
        // 파일 저장
        await workbook.xlsx.writeFile('테스트_엑셀.xlsx');
        console.log('엑셀 파일이 생성되었습니다.');
    }
    catch (error) {
        console.error('에러 발생:', error);
    }
}
createExcel();
