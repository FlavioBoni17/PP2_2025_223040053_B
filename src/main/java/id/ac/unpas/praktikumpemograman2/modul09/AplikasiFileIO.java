/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class AplikasiFileIO extends JFrame {
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    //Latihan 3
    private JButton btnSaveObject, btnLoadObject;
    
    //Latihan 4
    private JButton btnAppendText;

    
    public AplikasiFileIO() 
    {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config {Binary}");
        btnLoadBinary = new JButton("Buat Congfig {Binary}");
        
        //Latihan 3
        btnSaveObject = new JButton("Simpan Objek");
        btnLoadObject = new JButton("Muat Objek");
        
        //Latihan 4
        btnAppendText = new JButton("Simpan Text {Append}");
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        
        //Latihan 3
        buttonPanel.add(btnSaveObject);
        buttonPanel.add(btnLoadObject);
        
        //Latihan 4
        buttonPanel.add(btnAppendText);
        
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
        //Latihan 3
        btnSaveObject.addActionListener(e -> simpanUserConfig());
        btnLoadObject.addActionListener(e -> muatUserConfig());
        
        //Latihan 4
        btnAppendText.addActionListener(e -> appendFileTeks());
                
        bacaLastNotes();
    }
    
    private void bukaFileTeks() 
    {
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null;
            
            try
            {
               reader = new BufferedReader(new FileReader(file));
               textArea.setText("");
               
               String line;
               
               while((line = reader.readLine()) != null) 
               {
                   textArea.append(line + "\n");
               }
               
               JOptionPane.showMessageDialog(this, "File berhasil dimuat");
            }
            catch(FileNotFoundException ex) 
            {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan" + ex.getMessage());
            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Gagal membaca file" + ex.getMessage());
            }
            finally
            {
                try 
                {
                    if (reader != null)
                    {
                        reader.close();
                    }
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void simpanFileTeks()
    {
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan");
            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file" + ex.getMessage());
            }
        }
    }
    
    private void simpanConfigBinary() 
    {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin")))
        {
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, "Ukuran Font (" + fontSize + ") disimpan");
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }
    
    public void muatConfigBinary()
    {
        try(DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) 
        {
            int fontSize = dis.readInt();
            
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        }
        catch(FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }
    
    //Latihan 2
    private void bacaLastNotes() 
    {
        File file = new File("last_notes.txt");

        if (!file.exists()) 
        {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            textArea.setText("");
            String line;

            while ((line = reader.readLine()) != null) 
            {
                textArea.append(line + "\n");
            }

        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    //Latihan 3
    private void simpanUserConfig() 
    {
        try 
        {
            UserConfig config = new UserConfig();
            config.setUsername("Flavio");
            config.setFontsize(textArea.getFont().getSize());

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userconfig.bin"));

            oos.writeObject(config);
            oos.close();

            JOptionPane.showMessageDialog(this, "Objek UserConfig berhasil disimpan!");

        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
        }
    }
    
    private void muatUserConfig() 
    {
        try 
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userconfig.bin"));

            UserConfig config = (UserConfig) ois.readObject();
            ois.close();

            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));

            JOptionPane.showMessageDialog(this, "User: " + config.getUsername() + "\nFont Size: " + config.getFontsize());

        } 
        catch (FileNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(this, "File userconfig.bin belum ditemukan!");
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(this, "Gagal memuat objek: " + ex.getMessage());
        }
    }
    
    //Latihan 4
    private void appendFileTeks() 
    {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) 
            { 
                writer.write(textArea.getText());
                writer.newLine(); 
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan (append).");
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan text: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}


