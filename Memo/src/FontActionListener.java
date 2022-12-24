import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontActionListener implements ActionListener {
    private Main main;

    String cmd;

    public FontActionListener(Main main){
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cmd = e.getActionCommand();

        switch (cmd){
            case "Word Wrap":
                main.wordWrap();
                break;
            case "돋움":
                main.setFont(cmd);
                break;
            case "고딕":
                main.setFont(cmd);
                break;
            case "맑은 고딕":
                main.setFont(cmd);
                break;
            case "8":
                main.createNewFont(8);
                break;
            case "10":
                main.createNewFont(10);
                break;
            case "12":
                main.createNewFont(12);
                break;
            case "16":
                main.createNewFont(16);
                break;
            case "20":
                main.createNewFont(20);
                break;
            case "24":
                main.createNewFont(24);
                break;
            case "28":
                main.createNewFont(28);
                break;
        }
    }
}
