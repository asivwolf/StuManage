
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DelSC extends JPanel implements ActionListener{
	String saveC=null;
	String saveS=null;
	JTextField �κ�1,ѧ��1,ѧ��,�ɼ�,�κ�;
	JButton ɾ��,��ѯ;
    DelSC()
    {
     �κ�1=new JTextField(10);
        ѧ��1=new JTextField(10);
        ѧ��=new JTextField(10);
        �κ�1=new JTextField(10);
        �κ�=new JTextField(10);
        �ɼ�=new JTextField(10);
        ɾ��=new JButton("ɾ��");
        ��ѯ=new JButton("��ѯ");
        Box box1=Box.createHorizontalBox();
        Box box2=Box.createHorizontalBox();
        Box box3=Box.createHorizontalBox();
        Box box4=Box.createHorizontalBox();
        Box box5=Box.createHorizontalBox();
        box1.add(new JLabel("�κ�:",JLabel.CENTER));
        box1.add(�κ�);
        box2.add(new JLabel("ѧ��:",JLabel.CENTER));
        box2.add(ѧ��);
        box4.add(ɾ��);
        box3.add(new JLabel("�κ�:",JLabel.CENTER));
        box3.add(�κ�1);
        box5.add(new JLabel("ѧ��:",JLabel.CENTER));
        box5.add(ѧ��1);//������ѯ��ѧ��
        box5.add(��ѯ);
        Box boxH=Box.createVerticalBox();//��������
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        ɾ��.addActionListener(this);
        ��ѯ.addActionListener(this);
        JPanel p=new JPanel();
        JPanel p1=new JPanel();
        p.add(boxH);
        p1.add(box3);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p);
        add(jsp,BorderLayout.CENTER);
        validate();
    }
    public void actionPerformed(ActionEvent e)
    {  
        String sql1="select * from sc where Cno='"+�κ�1.getText()+"' and Sno='"+ѧ��1.getText()+"'";
        ResultSet rs1=null;
        ResultSet rs=null;
        if(e.getSource()==��ѯ)
        {
            if(�κ�1.getText().equals("")||ѧ��1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"������κź�ѧ��","��ʾ",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                try{

                    Connection con =Tools.CONN();
                    Statement stmt = con.createStatement();
                    rs=stmt.executeQuery(sql1);
                    if(rs.next())
                    {
                        ѧ��.setText(rs.getString("Sno").trim());
                        �κ�.setText(rs.getString("Cno").trim());
                        �ɼ�.setText(rs.getString("grade").trim());
                        saveC=�κ�1.getText().trim();
                        saveS=ѧ��1.getText().trim();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"û������κŵ�ѧ��","��ʾ",JOptionPane.WARNING_MESSAGE);
                    }
                    con.close();
                    stmt.close();
                    rs.close();
                    
                }catch(SQLException e1){
                    System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
                }
                
            }

        }
        else if(e.getSource()==ɾ��)
        {
            String sql="delete from sc where Cno='"+saveC+"' and Sno='"+saveS+"'";
            if(saveC==null||saveS==null)
            {
                JOptionPane.showMessageDialog(this,"���Ȳ�ѯ","��ʾ",JOptionPane.WARNING_MESSAGE);
            }
            else {
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "ɾ���ɹ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
                    con.close();
                    stmt.close();
                } catch (SQLException e1) {
                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                }
            }
        }
    }

}
