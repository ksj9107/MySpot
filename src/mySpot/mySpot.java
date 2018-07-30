package mySpot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class mySpot {
	public static void main(String[] args) {
		// �����ͺ��̽� ������ ���� ������
		
		
		//1 �����ͺ��̽��� ��ġ�� ��ǻ���� IP : 'localhost'Ű���� = ip�ּҷ� ġȯ�ؼ� �ν�
		String url = "localhost";
		//2 �����ͺ��̽��� �����ϴ� ��Ʈ��ȣ : ����(���ͳ�, �����ͺ��̽� ��)�� ��ȣ 
		String port = "3306";
		//3 �����ͺ��̽��� �����ϴ� ������
		String user = "root";
		//4 �����ͺ��̽��� �����ϴ� ������ ��й�ȣ
		String pass = "cjgreen";
		//5 ���̺��� ���ִ� �����ͺ��̽��� �����Ѵ�.
		String database = "world";
		
		
		// �ڹ����α׷��� �����ͺ��̽��� �����ϴ� ������ �ϴ� ��ü
		// Connection ��ü�� �����ϴ� ������ �����.
		Connection conn= null;
		// Statement ��ü�� �����ϴ� ������ �����. 
		// : SQL������ �����ͺ��̽����� ��û�ϴ� ������ �ϴ� ��ü
		Statement st = null;
		// Resultset ��ü�� �����ϴ� ������ �����.
		// : �����ͺ��̽��κ��� �޾ƿ� ������ �޴� ������ �ϴ� ��ü
		ResultSet rs = null;
		
/*		try{
			// ������ ����ϴ� connection ��ü�� �����.
			conn = DriverManager.getConnection(
					"jdbc:mysql://"+url+":"+port+
					"/"+database+"?useUnicode=true&charactorEncoding=euckr&useSSL=false"
					,user,pass);
			// SQL������ �����ͺ��̽����� ��û�ϴ� Statement ��ü�� �����.
			st = conn.createStatement();
			// SQL���� ��û�ϴ� �޼ҵ�(executeQuery())�� SQL ������ ����
			// ���� ������� rs ������ ����
			rs = st.executeQuery("SELECT * FROM school;");
			
			// rs.next() ������� ù��° ����� �������� �ִ��� �˻��ϴ� �޼ҵ�
			// �������� �ִٸ� ���ϰ��� true, �������� ���ٸ� ���ϰ��� false
			// �ι�°�� rs.next() �޼ҵ带 �����ϸ� ������ ���������� ����ȴ�.
			// 1��° rs.next() ������� ù��°�� ����
			// 2��° rs.next() ������� �ι�°�� ����
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
//				String name2 = rs.getString(3);
//				String age = rs.getString(4);
			System.out.println("id : "+ id + "    Lastname : "+name+"     Firstname : "
				+name2+"      age : "+age);
			}
			// �����ͺ��̽��� �����͸� �߰��ϰų� �����ϴ� ��쿡
			// INSERT, UPDATE���� Ȱ���� ���� executeUpdate()�޼ҵ带 Ȱ���Ѵ�.
//			st.executeUpdate("INSERT INTO school(name) VALUES('�������б�');");
//			st.executeUpdate("UPDATE school SET name='�ѱ�����б�';");
			
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
				// ������� �Է��� �޴� �κ�
				System.out.println("==================================");
				System.out.println("�ݰ����ϴ�. ���� �Խ��� �Դϴ�.");
				System.out.println("==================================");
				System.out.println("1 : �� ��� ���� ");
				System.out.println("2 : �� �ۼ� �ϱ� ");
				System.out.println("3 : �� ���� �ϱ� ");
				System.out.println("4 : �����ϱ� ");
				
				System.out.print("�޴��� �����ϼ��� : ");
				menu = sc.nextInt();
				
				switch(menu){
				case 1:
					rs = st.executeQuery("SELECT * FROM contents WHERE enable = '1'");
					System.out.println("�ۼ��� ���� ����Դϴ�.");
					System.out.println("�� ��ȣ\t�� ����\t�� �ۼ���\t����");
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
					System.out.print("���� ������ �Է��ϼ��� : ");
					String nwtitle = sc.nextLine();
					System.out.print("���� �ۼ��ڸ� �Է��ϼ��� : ");
					String nwwriter = sc.nextLine();
					System.out.print("���� ������ �Է��ϼ��� : ");
					String nwcontents = sc.nextLine();
					st.executeUpdate("INSERT INTO contents(title,writer,contents,create_at,update_at)"
							+ " VALUES('"+nwtitle+"','"+nwwriter+"','"+nwcontents+"',now(),now())");
					break;
					
				case 3: 
					System.out.print("������ ���� ��ȣ�� �Է��ϼ��� : ");
					int delid = sc.nextInt();
					st.executeUpdate("UPDATE contents set enable=0 WHERE id = "+delid);
					break;
					
				case 4:
					System.out.println("�Խ����� �ݽ��ϴ�.");
					return;
					
				default:
					System.out.println("1~4�� �߿� ������ �ּ���.");
				}
			}
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		sc.close();
	}
}
