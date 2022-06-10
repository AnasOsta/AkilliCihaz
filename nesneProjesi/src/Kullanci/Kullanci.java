package Kullanci;
import java.sql.*;

public class Kullanci {
    private static final int minSifre = 10000000;
    private Connection con = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public Kullanci ()
    {

    }

    private boolean sifreControlEt (int sifre){
        if (sifre < minSifre)
            return false;
        else
            return true;
    }

    private Connection baglan(){

        System.out.println("Veritabanina Baglaniyor.....");
        Connection conn = null;
        try
        {   /** Bağlantı kurulumu **/
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nesneProje", "anas", "741520");
            if (conn != null)
                System.out.println("Veritabanina baglandi.....");
            else
                System.out.println("Baglanti girisimi başarisiz.....");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public boolean yeniKullanci (String kullanciAdi, int sifre)
    {

        if (!sifreControlEt(sifre))
            return false;

        boolean benzer = true;
        sql = "INSERT INTO kullanci(kullanciadi, sifre) VALUES(\'" + kullanciAdi + "\', \'" + sifre + "\')";
        con = this.baglan();
        try{
            statement = con.createStatement();

            resultSet = statement.executeQuery("select * from kullanci");

            while (resultSet.next()) {
                String name = resultSet.getString("kullanciadi");

                if (kullanciAdi.equals(name))
                    benzer = false;
            }


            if (benzer){
                statement.executeUpdate(sql);
                return true;
            }


            /** Bağlantı sonlandırma **/
            con.close();
            /** Kaynakları serbest bırak **/
            statement.close();

        }catch (Exception e)
        {
            System.out.println(e);
        }

        return false;
    }

    public boolean girisYap (String kullanciAdi, int sifre)
    {
        con = this.baglan();
        try{
            statement = con.createStatement();

            resultSet = statement.executeQuery("select * from kullanci");

            while (resultSet.next())
            {
                String name = resultSet.getString("kullanciadi");
                String password = resultSet.getString("sifre");
                int val = Integer.parseInt(password);

                if (kullanciAdi.equals(name)) {
                    if (sifre == val)
                        return true;
                }

            }

            /** Bağlantı sonlandırma **/
            con.close();
            /** Kaynakları serbest bırak **/
            statement.close();

        }catch (Exception e)
        {
            System.out.println(e);
        }

        return false;
    }


}
