package mySpot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class mySpot {
	public static void main(String[] args) {
		// 데이터베이스 연결을 위한 정보들
		
		
		//1 데이터베이스가 설치된 컴퓨터의 IP : 'localhost'키워드 = ip주소로 치환해서 인식
		String url = "localhost";
		//2 데이터베이스가 동작하는 포트번호 : 서비스(인터넷, 데이터베이스 등)의 번호 
		String port = "3306";
		//3 데이터베이스에 접속하는 계정명
		String user = "root";
		//4 데이터베이스에 접속하는 계정의 비밀번호
		String pass = "cjgreen";
		//5 테이블이 모여있는 데이터베이스를 선택한다.
		String database = "world";
		
		
		// 자바프로그램과 데이터베이스를 연결하는 역할을 하는 객체
		// Connection 객체를 저장하는 변수를 만든다.
		Connection conn= null;
		// Statement 객체를 저장하는 변수를 만든다. 
		// : SQL문으로 데이터베이스에게 요청하는 역할을 하는 객체
		Statement st = null;
		// Resultset 객체를 저장하는 변수를 만든다.
		// : 데이터베이스로부터 받아온 쿼리를 받는 역할을 하는 객체
		ResultSet rs = null;
		
/*		try{
			// 연결을 담당하는 connection 객체를 만든다.
			conn = DriverManager.getConnection(
					"jdbc:mysql://"+url+":"+port+
					"/"+database+"?useUnicode=true&charactorEncoding=euckr&useSSL=false"
					,user,pass);
			// SQL문으로 데이터베이스에게 요청하는 Statement 객체를 만든다.
			st = conn.createStatement();
			// SQL문을 요청하는 메소드(executeQuery())로 SQL 문장을 실행
			// 실행 결과물이 rs 변수에 저장
			rs = st.executeQuery("SELECT * FROM school;");
			
			// rs.next() 결과물의 첫번째 행부터 다음행이 있는지 검사하는 메소드
			// 다음행이 있다면 리턴값은 true, 다음행이 없다면 리턴값은 false
			// 두번째로 rs.next() 메소드를 실행하면 기준이 다음행으로 변경된다.
			// 1번째 rs.next() 결과물의 첫번째행 기준
			// 2번째 rs.next() 결과물의 두번째행 기준
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
//				String name2 = rs.getString(3);
//				String age = rs.getString(4);
			System.out.println("id : "+ id + "    Lastname : "+name+"     Firstname : "
				+name2+"      age : "+age);
			}
			// 데이터베이스에 데이터를 추가하거나 수정하는 경우에
			// INSERT, UPDATE문을 활용할 때는 executeUpdate()메소드를 활용한다.
//			st.executeUpdate("INSERT INTO school(name) VALUES('동산고등학교');");
//			st.executeUpdate("UPDATE school SET name='한국고등학교';");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}*/
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		try{
			conn = DriverManager.getConnection(
					"jdbc:mysql://"+url+":"+port+
					"/"+database+"?useUnicode=true&charactorEncoding=euckr&useSSL=false"
					,user,pass);
			st = conn.createStatement();
			while(true){
				// 사용자의 입력을 받는 부분
				System.out.println("==================================");
				System.out.println("반갑습니다. 마이 게시판 입니다.");
				System.out.println("==================================");
				System.out.println("1 : 글 목록 보기 ");
				System.out.println("2 : 글 작성 하기 ");
				System.out.println("3 : 글 삭제 하기 ");
				System.out.println("4 : 종료하기 ");
				
				System.out.print("메뉴를 선택하세요 : ");
				menu = sc.nextInt();
				
				switch(menu){
				case 1:
					rs = st.executeQuery("SELECT * FROM contents WHERE enable = '1'");
					System.out.println("작성한 글의 목록입니다.");
					System.out.println("글 번호\t글 제목\t글 작성자\t내용");
					while(rs.next()){
						String id = rs.getString(1);
						String title = rs.getString(2);
						String writer = rs.getString(3);
						String contents = rs.getString(4);
						System.out.println(id+"\t"+title+"\t"+writer+"\t"+contents);
					}
					break;
				
				case 2:
					sc.nextLine();
					System.out.print("글의 제목을 입력하세요 : ");
					String nwtitle = sc.nextLine();
					System.out.print("글의 작성자를 입력하세요 : ");
					String nwwriter = sc.nextLine();
					System.out.print("글의 내용을 입력하세요 : ");
					String nwcontents = sc.nextLine();
					st.executeUpdate("INSERT INTO contents(title,writer,contents,create_at,update_at)"
							+ " VALUES('"+nwtitle+"','"+nwwriter+"','"+nwcontents+"',now(),now())");
					break;
					
				case 3: 
					System.out.print("제거할 글의 번호를 입력하세요 : ");
					int delid = sc.nextInt();
					st.executeUpdate("UPDATE contents set enable=0 WHERE id = "+delid);
					break;
					
				case 4:
					System.out.println("게시판을 닫습니다.");
					return;
					
				default:
					System.out.println("1~4번 중에 선택해 주세요.");
				}
			}
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		sc.close();
	}
}
