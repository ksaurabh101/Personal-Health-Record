
import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Saurabh
 */
public class GetKey {

    public static String getPatientKey(String patientID) {
        Connection con;
        Statement st;
        ResultSet rs;
        String key = "";
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phr", "root", "");
            con = Config.getcon();
            st = con.createStatement();
            String query = "select * from patient where patientID='" + patientID + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                key = rs.getString("key");
                System.out.println("Key : " + key);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return key;
    }
}
