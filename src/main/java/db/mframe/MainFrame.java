package db.mframe;

import db.mysql.Main;
import db.mysql.MysqlCommon;
import db.mysql.RuntimeEnv;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * db.frame
 *
 * @author ASUS
 * @date 2017/10/21 10:15
 */
public class MainFrame extends JFrame{

    JButton cancelConnectBtn = new JButton("取消连接");

    JComboBox jcombo = new JComboBox();

    JTextField workOut=new JTextField();

    JTextField modelField=new JTextField();

    JTextField modelOut=new JTextField();

    JTextField mapperField=new JTextField();

    JTextField mapperOut=new JTextField();

    JTextField xmlField=new JTextField();

    JTextField xmlOut=new JTextField();

    JCheckBox overwrite=new JCheckBox("全覆盖生成");

    JCheckBox packgeFiler=new JCheckBox("生成包文件夹");

    {
        modelField.setPreferredSize(new Dimension(150,26));
        modelOut.setPreferredSize(new Dimension(150,26));
        mapperField.setPreferredSize(new Dimension(150,26));
        mapperOut.setPreferredSize(new Dimension(150,26));
        xmlField.setPreferredSize(new Dimension(150,26));
        xmlOut.setPreferredSize(new Dimension(150,26));
        jcombo.setPreferredSize(new Dimension(150,26));
        workOut.setPreferredSize(new Dimension(150,26));

    }


    private static Dimension titleSize ;

    private static Dimension inputSize;

    static {
        titleSize=new Dimension();
        titleSize.setSize(100,20);
        inputSize = new Dimension();
        inputSize.setSize(250,20);
    }

    public MainFrame (){
        this.setTitle("mybatis生成器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 950, 250);
        JPanel contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel pane1=new JPanel();
        contentPane.add(pane1);
        JPanel pane2=new JPanel();
        contentPane.add(pane2);
        JPanel pane3=new JPanel();
        contentPane.add(pane3);

        JLabel label1=new JLabel("url：");
        JTextField textField1=new JTextField();
        textField1.setColumns(10);
        textField1.setText(RuntimeEnv.pp.getUrl());
        pane1.add(label1);
        pane1.add(textField1);

        JLabel schemaLabel=new JLabel("schema：");
        JTextField schemaField=new JTextField();
        schemaField.setColumns(10);
        schemaField.setText(RuntimeEnv.pp.getSchema());
        pane1.add(schemaLabel);
        pane1.add(schemaField);

        JLabel label2=new JLabel("user：");
        JTextField textField2=new JTextField();
        textField2.setColumns(10);
        textField2.setText(RuntimeEnv.pp.getUser());
        pane1.add(label2);
        pane1.add(textField2);
        JLabel label3=new JLabel("password：");
        JTextField textField3=new JTextField();
        textField3.setColumns(10);
        textField3.setText(RuntimeEnv.pp.getPassword());
        pane1.add(label3);
        pane1.add(textField3);
        JButton connectBtn = new JButton("连接");
        connectBtn.addActionListener(actionEvent->{
            RuntimeEnv.pp.setUrl(textField1.getText());
            RuntimeEnv.pp.setUser(textField2.getText());
            RuntimeEnv.pp.setSchema(schemaField.getText());
            RuntimeEnv.pp.setPassword(textField3.getText());
            RuntimeEnv.storage();
            RuntimeEnv.mc = new MysqlCommon();
            connectBtn.setEnabled(false);
            connectBtn.setText("连接成功");
            cancelConnectBtn.setEnabled(true);
            textField1.setEnabled(false);
            schemaField.setEnabled(false);
            textField2.setEnabled(false);
            textField3.setEnabled(false);
            pane2.setVisible(true);
            pane3.setVisible(true);
            System.out.println("connect");
            jcombo.removeAllItems();
            final String[] selected = {null};
            for (String table :RuntimeEnv.mc.getTableList()){
                jcombo.addItem(table);
                if (table.equals(RuntimeEnv.pp.getTableName())){
                    selected[0] =table;
                }
            }
            if (selected[0]!=null){
                jcombo.setSelectedItem(selected[0]);
            }
        });
        pane1.add(connectBtn);

        cancelConnectBtn.setEnabled(false);
        cancelConnectBtn.addActionListener(actionEvent->{
            connectBtn.setEnabled(true);
            connectBtn.setText("连接");
            cancelConnectBtn.setEnabled(false);
            textField1.setEnabled(true);
            schemaField.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            pane2.setVisible(false);
            pane3.setVisible(false);
        });
        pane1.add(cancelConnectBtn);
        this.setVisible(true);
        pane2.setVisible(false);
        pane2.add(jcombo);

        JPanel innerJp0 = new JPanel();
        innerJp0.setLayout(new GridLayout(3,1,5,5));
        pane2.add(innerJp0);
        workOut.setText(RuntimeEnv.pp.getWorkSpace());
        innerJp0.add(workOut);
        JButton workBtn = new JButton("选择工作目录");
        workOut.setPreferredSize(new Dimension(150,26));
        workBtn.addActionListener(actionEvent->{
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION ){
                //解释下这里,弹出个对话框,可以选择要上传的文件,如果选择了,就把选择的文件的绝对路径打印出来,有了绝对路径,通过JTextField的settext就能设置进去了,那个我没写
                RuntimeEnv.pp.setWorkSpace(jfc.getSelectedFile().getAbsolutePath().replaceAll("\\\\","/"));
                workOut.setText(RuntimeEnv.pp.getWorkSpace());
            }
        });
        innerJp0.add(workBtn);


        JPanel innerJp1 = new JPanel();
        innerJp1.setLayout(new GridLayout(3,1,5,5));
        pane2.add(innerJp1);
        modelField.setText(RuntimeEnv.pp.getClassName());
        modelOut.setText(RuntimeEnv.pp.getPackageModel());
        innerJp1.add(modelField);
        innerJp1.add(modelOut);

        JPanel innerJp2 = new JPanel();
        innerJp2.setLayout(new GridLayout(3,1,5,5));
        pane2.add(innerJp2);
        mapperField.setText(RuntimeEnv.pp.getMapperName());
        mapperOut.setText(RuntimeEnv.pp.getPackageMapper());
        innerJp2.add(mapperField);
        innerJp2.add(mapperOut);

        JPanel innerJp3 = new JPanel();
        innerJp3.setLayout(new GridLayout(3,1,5,5));
        pane2.add(innerJp3);
        xmlField.setText(RuntimeEnv.pp.getMapperXmlName());
        xmlOut.setText(RuntimeEnv.pp.getPackageXmlMapper());
        innerJp3.add(xmlField);
        innerJp3.add(xmlOut);

        modelField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                mapperField.setText(modelField.getText()+"Mapper");
                xmlField.setText(modelField.getText()+"Mapper");
            }
        });
        mapperField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                xmlField.setText(mapperField.getText());
            }
        });
        pane3.setVisible(false);
        overwrite.setSelected(RuntimeEnv.pp.isOverwrite());
        overwrite.addActionListener(actionEvent->{
            RuntimeEnv.pp.setOverwrite(overwrite.getSelectedObjects()!=null);
        });

        pane3.add(overwrite);

        packgeFiler.setSelected(RuntimeEnv.pp.isProducePackFile());
        packgeFiler.addActionListener(actionEvent->{
            RuntimeEnv.pp.setProducePackFile(packgeFiler.getSelectedObjects()!=null);
        });

        pane3.add(packgeFiler);

        JButton generate = new JButton("生成模板");
        generate.addActionListener(actionEvent->{
            boolean pack=packgeFiler.getSelectedObjects()!=null;
            RuntimeEnv.pp.setProducePackFile(pack);
            RuntimeEnv.pp.setWorkSpace(workOut.getText());
            RuntimeEnv.pp.setTableName((String) jcombo.getSelectedItem());
            RuntimeEnv.pp.setClassName(modelField.getText());
            RuntimeEnv.pp.setPackageModel(modelOut.getText());
            RuntimeEnv.pp.setModelOutPath(workOut.getText()+(pack?"/"+RuntimeEnv.pp.getPackageModel().replaceAll("\\.","/"):""));
            RuntimeEnv.pp.setMapperName(mapperField.getText());
            RuntimeEnv.pp.setPackageMapper(mapperOut.getText());
            RuntimeEnv.pp.setMapperOutPath(workOut.getText()+(pack?"/"+RuntimeEnv.pp.getPackageMapper().replaceAll("\\.","/"):""));
            RuntimeEnv.pp.setMapperXmlName(xmlField.getText());
            RuntimeEnv.pp.setPackageXmlMapper(xmlOut.getText());
            RuntimeEnv.pp.setMapperXmlOutPath(workOut.getText()+(pack?"/"+RuntimeEnv.pp.getPackageXmlMapper().replaceAll("\\.","/"):""));
            RuntimeEnv.pp.setOverwrite(overwrite.getSelectedObjects()!=null);
            Main.generate();
            RuntimeEnv.storage();
            JOptionPane.showMessageDialog(null,"生成成功");
        });
        pane3.add(generate);
    }
}
