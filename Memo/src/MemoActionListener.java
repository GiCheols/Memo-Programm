import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class MemoActionListener implements ActionListener {
    private Main memo;
    String cmd;

    public MemoActionListener(Main memo){
        this.memo = memo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cmd = e.getActionCommand();

        switch (cmd){
            case "File":
                memo.newFile();
            case "Open":
                memo.openFile();
            case "Save":
                if(memo.fileName.equals("")){
                    int ret = memo.fileChooser.showSaveDialog(null);

                    if(ret != JFileChooser.APPROVE_OPTION){
                        JOptionPane.showMessageDialog(null,
                                "파일을 선택하지 않았습니다.",
                                "경고", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    memo.fileName = memo.fileChooser.getSelectedFile().getPath();
                }
                memo.saveFile(memo.textArea.getText());

                break;
            case "SaveAs":
                int ret = memo.fileChooser.showSaveDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    memo.fileName = memo.fileChooser.getSelectedFile().getPath();
                    memo.saveFile(memo.textArea.getText());
                }
                break;
            case "Exit":
                exit(0);
        }
    }
}
