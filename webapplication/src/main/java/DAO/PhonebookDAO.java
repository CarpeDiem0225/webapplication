package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import VO.PhonebookVO;

@Repository
public class PhonebookDAO {
    private Connection conn;
    
    public PhonebookDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
            System.out.println("DB 연결 성공: " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. 입력
    public boolean insert(PhonebookVO pb) {
        String sql = "INSERT INTO phonebook (id, name, hp, memo) VALUES (phonebook_id_seq.NEXTVAL, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pb.getName());
            ps.setString(2, pb.getHp());
            ps.setString(3, pb.getMemo());
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. 전체출력
    public List<PhonebookVO> selectAll() {
        List<PhonebookVO> list = new ArrayList<>();
        String sql = "SELECT * FROM phonebook";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String hp = rs.getString("hp");
                String memo = rs.getString("memo");
                
                PhonebookVO pb = new PhonebookVO(id, name, hp, memo);
                list.add(pb);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

 // 3. 모든 항목 선택 출력
    public List<PhonebookVO> search(String str) {
        List<PhonebookVO> sList = new ArrayList<>();
        String sql = "SELECT * FROM phonebook WHERE name LIKE ? OR hp LIKE ? OR memo LIKE ?";
        
        try {
        	PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, str);
            ps.setString(2, str);
            ps.setString(3, str);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String hp = rs.getString("hp");
                    String memo = rs.getString("memo");
                    
                    PhonebookVO pb = new PhonebookVO(id, name, hp, memo);
                    sList.add(pb);
                }
                rs.close();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sList;
    }


    // 4. 선택 출력
    public PhonebookVO selectById(int id) {
        String sql = "SELECT * FROM phonebook WHERE id = ?";
        PhonebookVO pb = null;
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("name");
                String hp = rs.getString("hp");
                String memo = rs.getString("memo");
                
                pb = new PhonebookVO(id, name, hp, memo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pb;
    }

    // 5. 수정
    public boolean update(PhonebookVO pb) {
        String sql = "UPDATE phonebook SET name = ?, hp = ?, memo = ? WHERE id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pb.getName());
            ps.setString(2, pb.getHp());
            ps.setString(3, pb.getMemo());
            ps.setInt(4, pb.getId());
            
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6. 삭제
    public boolean delete(int id) {
        String sql = "DELETE FROM phonebook WHERE id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            int result = ps.executeUpdate();
            ps.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
