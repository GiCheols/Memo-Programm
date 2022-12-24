import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    JFrame frame;
    boolean wordWrapOn = false;
    JFileChooser fileChooser = new JFileChooser();
    JTextArea textArea = new JTextArea();
    JMenuBar menuBar = new JMenuBar();
    String fileName = "";

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        buildGUI();
        newTextArea();
        newMenuBar();
        fileMenu();
        formatMenu();
        frame.setVisible(true);
    }

    public void buildGUI() {
        frame = new JFrame("Memo Program");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void newTextArea() {
        textArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);
    }

    String[] menuType = {"File", "Format"};
    ArrayList<JMenu> menuBars = new ArrayList<>(5);

    public void newMenuBar() {
        frame.setJMenuBar(menuBar);

        for (String n : menuType) {
            menuBars.add(new JMenu(n));
        }
        for (JMenu bar : menuBars)
            menuBar.add(bar);
    }

    String[] menuItem = {"File", "Open", "Save", "SaveAs", "Exit"};
    ArrayList<JMenuItem> menuItems = new ArrayList<>(5);

    public void fileMenu() {
        for (String s : menuItem) {
            menuItems.add(new JMenuItem(s));
        }

        for (JMenuItem item : menuItems) {
            MemoActionListener memoActionListener = new MemoActionListener(this);
            item.addActionListener(memoActionListener);
            frame.setJMenuBar(menuBar);
            menuBars.get(0).add(item);
        }
    }

    String [] formatMenuItem = {"Font Type", "Font Size"};
    String [] fontSize = {"8", "10", "12", "16", "20", "24", "28"};
    String [] fontType = {"돋움", "고딕", "맑은 고딕"};
    JMenuItem wordWrapMenu;
    ArrayList<JMenu> formatMenuItems = new ArrayList<>(3);
    ArrayList<JMenuItem> formatFontTypes = new ArrayList<>(3);
    ArrayList<JMenuItem> formatFontSizeItems = new ArrayList<>(7);
    public void formatMenu(){
        FontActionListener fontActionListener = new FontActionListener(this);

        wordWrapMenu = new JMenuItem("Word Wrap: Off");
        wordWrapMenu.addActionListener(fontActionListener);
        wordWrapMenu.setActionCommand("Word Wrap");
        menuBars.get(1).add(wordWrapMenu);

        for(String s : formatMenuItem)
            formatMenuItems.add(new JMenu(s));

        for(String s : fontType)
            formatFontTypes.add(new JMenuItem(s));

        for(String s : fontSize)
            formatFontSizeItems.add(new JMenuItem(s));

        for (JMenu item : formatMenuItems) {
            frame.setJMenuBar(menuBar);
            menuBars.get(1).add(item);
        }

        for(JMenuItem formatFontType : formatFontTypes){
            formatFontType.addActionListener(fontActionListener);
            frame.setJMenuBar(menuBar);
            formatMenuItems.get(0).add(formatFontType);
        }

        for (JMenuItem formatFontSizeItem : formatFontSizeItems) {
            formatFontSizeItem.addActionListener(fontActionListener);
            frame.setJMenuBar(menuBar);
            formatMenuItems.get(1).add(formatFontSizeItem);
        }
    }

    public void newFile() {
        textArea.setText("");
    }

    public void openFile() {
        int ret = fileChooser.showOpenDialog(null);

        if (ret != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "파일을 선택하지 않았습니다.",
                    "경고", JOptionPane.WARNING_MESSAGE);
        } else {
            File inFile = fileChooser.getSelectedFile();
            BufferedReader in;
            try {
                in = new BufferedReader(new FileReader(inFile));
                String c;
                textArea.setText("");
                while ((c = in.readLine()) != null) {
                    textArea.append(c + "\r\n");
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileName = fileChooser.getSelectedFile().toString();
        frame.setTitle(fileChooser.getSelectedFile().getName());
    }

    public void saveFile(String fileName) {
        BufferedWriter bw;
        File file = new File(this.fileName);
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(fileName);
            frame.setTitle(file.getName());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wordWrap(){
        if(!wordWrapOn){
            wordWrapOn = true;
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            wordWrapMenu.setText("Word Wrap: On");
        }
        else {
            wordWrapOn = false;
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);
            wordWrapMenu.setText("Word Wrap: Off");
        }
    }

    String usingFont = "Serif";
    Font dodUm = new Font("돋움", Font.PLAIN, 12);
    Font gothic = new Font("고딕", Font.PLAIN, 12);
    Font clearGothic = new Font("맑은 고딕", Font.PLAIN, 12);
    public void createNewFont(int fontSize){
        dodUm = new Font("돋움", Font.PLAIN, fontSize);
        gothic = new Font("고딕", Font.PLAIN, fontSize);
        clearGothic = new Font("맑은 고딕", Font.PLAIN, fontSize);

        setFont(usingFont);
    }

    public void setFont(String font){
        usingFont = font;

        switch (usingFont){
            case "돋움":
                textArea.setFont(dodUm);
                break;
            case "고딕":
                textArea.setFont(gothic);
                break;
            case "맑은 고딕":
                textArea.setFont(clearGothic);
                break;
        }
    }
}
