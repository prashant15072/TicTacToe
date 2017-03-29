import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * Created by PRASHANT on 10/16/2016.
 */
public class T_T_Tgame extends JFrame{

    private int val,print=1,global_count=0,player_chance,first_timer=0,flag=0;
    private JButton grid_buttons[];
    private String username01,username02;
    private JLabel output_box;

    T_T_Tgame()
    {
        grid_buttons=new JButton[9];
        setSize(400,410);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        front_page obj =new front_page();
    }

    private void disable_buttons(){
        for (int i=0;i<9;i++){
            grid_buttons[i].setEnabled(false);
        }
    }

    private void enable_buttons(){
        for (int i=0;i<9;i++){
            grid_buttons[i].setEnabled(true);
        }
    }

    private int player_chooser(String user1,String user2){
        int result;
        if (val==1){
            JButton one=new JButton(user1);
            JButton two =new JButton(user2);
            Object array[]={one.getText(),two.getText()};
            result = JOptionPane.showOptionDialog(null,"Who would like to go first :", "Choose", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,array,array[0]);
        }
        else if (val==2){
            JButton one=new JButton(user1);
            JButton two =new JButton("CPU");
            Object array[]={one.getText(),two.getText()};
            result = JOptionPane.showOptionDialog(null,"Who would like to go first :", "Choose", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,array,array[0]);
        }
        else if (val ==3){
            JButton one=new JButton(user1);
            JButton two =new JButton("AI");
            Object array[]={one.getText(),two.getText()};
            result = JOptionPane.showOptionDialog(null,"Who would like to go first :", "Choose", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,array,array[0]);
        }
        else{
            JButton one=new JButton("AI");
            JButton two =new JButton("CPU");
            Object array[]={one.getText(),two.getText()};
            result = JOptionPane.showOptionDialog(null,"Who would like to go first :", "Choose", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,array,array[0]);
        }
        return result;
    }

    private void change(JButton d,JButton a,JButton b,JButton c){
        d.setForeground(Color.CYAN);
        a.setForeground(null);
        b.setForeground(null);
        c.setForeground(null);
        flag++;
        if (flag>1){
            re();
        }
    }

    private class game{
        game(){
            getContentPane().removeAll();
            JPanel game_page=new JPanel(null);

            //PANEL FOR BUTTONS
            JPanel buttons =new JPanel(new GridLayout(1,4));
            buttons.setBounds(0,0,384,50);

            //buttons.setBackground(Color.RED);
            JButton UU=new JButton("User Vs User");
            JButton UC=new JButton("User Vs CPU");
            JButton CA=new JButton("CPU vs AI");
            JButton UA=new JButton("User Vs AI");


            UU.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
            UU.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    val=1;
                    change(UU,UC,CA,UA);
                    JTextField username1 = new JTextField(15);
                    JTextField username2=new JTextField(15);

                    JPanel myPanel=new JPanel();
                    myPanel.add(new JLabel("User1:"));
                    myPanel.add(username1);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("User2:"));
                    myPanel.add(username2);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "Enter Username", JOptionPane.OK_CANCEL_OPTION);

                    while ((username1.getText().trim().length() == 0 | username2.getText().trim().length() == 0 ) && cancel(result)!=1 ){
                        username1.setBackground(Color.red);
                        username2.setBackground(Color.red);
                        username1.setForeground(Color.white);
                        username2.setForeground(Color.white);
                        result = JOptionPane.showConfirmDialog(null, myPanel,
                                "Please Correctly Enter Username", JOptionPane.OK_CANCEL_OPTION);
                    }
                    if (cancel(result)!=1) {
                        player_chance = player_chooser(username1.getText(), username2.getText());
                        enable_buttons();
                        username01 = username1.getText();
                        username02 = username2.getText();
                        if (first_timer == 0) {
                            if (player_chance == 0) {
                                output_box.setText(username01 + "'s Turn");
                            } else {
                                output_box.setText(username02 + "'s Turn");
                            }
                        }
                    }
                }
            });
            buttons.add(UU);

            UC.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
            UC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    val=2;
                    change(UC,UU,CA,UA);
                    JTextField username1 = new JTextField(15);
                    JPanel myPanel=new JPanel();
                    myPanel.add(new JLabel("Name :"));
                    myPanel.add(username1);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "Enter Username", JOptionPane.OK_CANCEL_OPTION);

                    while (username1.getText().trim().length() == 0 && cancel(result)!=1){
                        username1.setBackground(Color.red);
                        username1.setForeground(Color.white);
                        result= JOptionPane.showConfirmDialog(null, myPanel,
                                "Please Correctly Enter Username", JOptionPane.OK_CANCEL_OPTION);
                    }
                    if (cancel(result)!=1) {
                        player_chance = player_chooser(username1.getText(), "CPU");
                        enable_buttons();
                        username01 = username1.getText();
                        username02 = "CPU";
                        if (first_timer == 0) {
                            if (player_chance == 0) {
                                output_box.setText(username01 + "'s Turn");
                            } else {
                                output_box.setText(username02 + "'s Turn");
                            }
                        }
                        if (player_chance == 1) {
                            JButton a = new JButton();
                            handler(2, a);
                        }
                    }
                }
            });
            buttons.add(UC);

            CA.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
            CA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    val=4;
                    change(CA,UC,UU,UA);
                    JButton a=new JButton();
                    player_chance=player_chooser("","");
                    enable_buttons();
                    username01="AI";
                    username02="CPU";
                    if (first_timer==0){
                        if (player_chance==0){
                            output_box.setText(username01+"'s Turn");
                        }
                        else{
                            output_box.setText(username02+"'s Turn");
                        }
                    }

                    handler(4,a);
                }
            });
            buttons.add(CA);

            UA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    val=3;
                    change(UA,UC,CA,UU);
                    JTextField username1 = new JTextField(15);
                    JPanel myPanel=new JPanel();
                    myPanel.add(new JLabel("Name :"));
                    myPanel.add(username1);
                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                            "Enter Username", JOptionPane.OK_CANCEL_OPTION);
                    if (cancel(result)!=1) {

                        while (username1.getText().trim().length() == 0 && cancel(result)!=1 ) {
                            username1.setBackground(Color.red);
                            username1.setForeground(Color.white);
                            result = JOptionPane.showConfirmDialog(null, myPanel,
                                    "Please Correctly Enter Username", JOptionPane.OK_CANCEL_OPTION);
                        }
                        if (cancel(result)!=1) {
                            player_chance = player_chooser(username1.getText(), "AI");
                            enable_buttons();
                            username01 = username1.getText();
                            username02 = "AI";
                            if (first_timer == 0) {
                                if (player_chance == 0) {
                                    output_box.setText(username01 + "'s Turn");
                                } else {
                                    output_box.setText(username02 + "'s Turn");
                                }
                            }
                            if (player_chance == 1) {
                                JButton a = new JButton();
                                handler(2, a);
                            }
                        }
                    }
                }
            });
            UA.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
            buttons.add(UA);


            game_page.add(buttons);


            //PANEL FOR GRID
            JPanel Grid =new JPanel(new GridLayout(3,3));
            Grid.setBounds(0,50,384,270);
            make_grid(Grid,val);
            disable_buttons();
            Grid.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
            game_page.add(Grid);


            //PANEL FOR OUTPUT
            JPanel Output=new JPanel(new GridLayout(0,1));
            Output.setBounds(0,290,384,80);
            Output.add(new JLabel());
            output_box=new JLabel("Welcome");
            output_box.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
            output_box.setFont(new Font("Serif", Font.PLAIN, 25));
            Output.add(output_box);
            JButton exit_button =new JButton("Exit");
            exit_button.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
            exit_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            Output.add(exit_button);

            game_page.add(Output);

            add(game_page);

        }
    }

    private int cancel(int val){
        if (val==JOptionPane.CANCEL_OPTION){
            return 1;
        }
        return 0;
    }

    private class front_page {

        front_page() {
            getContentPane().removeAll();
            JPanel h = new JPanel(null);

            JLabel heading = new JLabel("Tic-Tac-Toe");
            heading.setBounds(50, 20, 350, 150);
            heading.setFont(new Font("Serif", Font.PLAIN, 50));
            h.add(heading);

            JButton start_button = new JButton("Start");
            button_characteristics(start_button);
            start_button.setLocation(140, 150);
            start_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    h.setVisible(false);
                    remove(h);
                    game obj2 = new game();
                }
            });
            h.add(start_button);

            JButton exit_button = new JButton("Exit");
            button_characteristics(exit_button);
            exit_button.setLocation(140, 200);
            exit_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            h.add(exit_button);

            add(h);
            setVisible(true);
        }
    }

    private void button_characteristics(JButton button_name){
        button_name.setSize(110,30);
    }

    private void delay(){

    }

    private void handler(int i,JButton button){
        int k;
        if (i==1){
            global_count++;
            k=checkstate();
            if(print==0 && player_chance==0 || print==1 && player_chance==1){
                output_box.setText(username02+"'s Turn");
            }
            else{
                output_box.setText(username01+"'s Turn");
            }
        }
        else if (i==2){
            global_count++;
            k=checkstate();
            output_box.setText(username02+"'s Turn");
            complete(2,button);
            global_count++;
            k=checkstate();
            output_box.setText(username01+"'s Turn");
        }
        else if (i==3){
            global_count++;
            k=checkstate();
            output_box.setText(username02+"'s Turn");
            complete(3,button);
            global_count++;
            k=checkstate();
            output_box.setText(username01+"'s Turn");
        }
        else{
            int m = 0;
            for (int j = 0; j < 9; j++) {
                if (grid_buttons[j].isEnabled() == true) {
                    m++;
                }
            }

            int arr[] = new int[m];
            int h = 0;
            for (int j = 0; j < 9; j++) {
                if (grid_buttons[j].isEnabled() == true) {
                    arr[h] = j;
                    h++;
                }
            }
            while(m>0){
                if (player_chance==0){
                    output_box.setText(username01+"'s Turn");
                    complete(3,button);
                    global_count++;
                    k=checkstate();
                    delay();
                    output_box.setText(username02+"'s Turn");
                    complete(2,button);
                    global_count++;
                    k=checkstate();
                    delay();
                }
                else{
                    output_box.setText(username02+"'s Turn");
                    complete(2,button);
                    global_count++;
                    k=checkstate();
                    delay();
                    output_box.setText(username01+"'s Turn");
                    complete(3,button);
                    global_count++;
                    k=checkstate();
                    delay();
                }
                m = 0;
                for (int j = 0; j < 9; j++) {
                    if (grid_buttons[j].isEnabled() == true) {
                        m++;
                    }
                }
            }
        }
    }

    private void make_grid(JPanel a,int k){
        for (int i=0;i<9;i++){
            grid_buttons[i]=new JButton("");
            grid_buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            int j=i;
            grid_buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String a ="";
                    if (print==0){
                        print=1;
                        a="O";
                    }
                    else{
                        print=0;
                        a="X";
                    }
                    grid_buttons[j].setText(a);
                    grid_buttons[j].setEnabled(false);
                    handler(val,grid_buttons[j]);
                }
            });
            a.add(grid_buttons[i]);
        }

    }

    public static void main(String[] args){
        T_T_Tgame one=new T_T_Tgame();
    }

    private void re(){
        int dialogResult;
        dialogResult = JOptionPane.showConfirmDialog (null,"All the progress will be gone\nWould you like to restart\n ","Are you sure ?",JOptionPane.YES_NO_OPTION);
        if (dialogResult==JOptionPane.YES_OPTION){
            val=0;print=1;global_count=0;player_chance=0;first_timer=0;flag=0;
            username01="No one";username02="";
            output_box.setText("Welcome Back");
            getContentPane().removeAll();
            game obj2=new game();
        }
    }

    private void restart_popup(String a){
        String user;
        if (a=="X"){
            if (player_chance==0){
                output_box.setText(username01+" Won");
                user=username01;
            }
            else{
                output_box.setText(username02+" Won");
                user=username02;
            }
        }
        else{
            if (player_chance==0){
                output_box.setText(username02+" Won");
                user=username02;
            }
            else{
                output_box.setText(username01+" Won");
                user=username01;
            }
        }
        val=0;print=1;global_count=0;player_chance=0;first_timer=0;
        int dialogResult;
        if (a!="D"){
            dialogResult = JOptionPane.showConfirmDialog (null, user+" WON\n"+"Would you like to restart ",user+" WON",JOptionPane.YES_NO_OPTION);
        }
        else{
            dialogResult = JOptionPane.showConfirmDialog (null,"Its a Tie     "+"Would you like to restart ","Its a Tie",JOptionPane.YES_NO_OPTION);
        }
        if(dialogResult == JOptionPane.YES_OPTION){
            val=0;print=1;global_count=0;player_chance=0;first_timer=0;flag=0;
            username01="No one";username02="";
            output_box.setText("Welcome Back");
            getContentPane().removeAll();
            game obj2=new game();

        }
        else{
            dispose();
        }

    }

    private void complete(int c,JButton button){
        if (c==1){
            if (print==0){
                print=1;
                button.setText("O");
            }
            else{
                print=0;
                button.setText("X");
            }
            button.setEnabled(false);
        }

        else if (c==2) {
            if (global_count <10) {
                Random numbers = new Random();
                int m = 0;
                for (int j = 0; j < 9; j++) {
                    if (grid_buttons[j].isEnabled() == true) {
                        m++;
                    }
                }

                int arr[] = new int[m];
                int h = 0;
                for (int j = 0; j < 9; j++) {
                    if (grid_buttons[j].isEnabled() == true) {
                        arr[h] = j;
                        h++;
                    }
                }

                if (m > 0) {
                    int x = numbers.nextInt(m);
                    if (print == 0) {
                        print = 1;
                        grid_buttons[arr[x]].setText("O");
                    } else {
                        print = 0;
                        grid_buttons[arr[x]].setText("X");
                    }
                    grid_buttons[arr[x]].setEnabled(false);
                }
            }
        }

        else if (c==3){
            int a;
            a=AI(button);
        }
    }

    private int AI(JButton button){
        String a,b;
        int c;
        if (print==0){
            a="X";
            b="O";
            c=1;
        }
        else{
            a="O";
            b="X";
            c=0;
        }
        //row check
        int count=0,touse=0;
        int arr_return[];
        arr_return=new int[2];
        for (int i=0;i<9;i+=3){
            for (int j=0;j<3;j++){
                if (grid_buttons[i+j].getText()==a){
                    count++;
                }
                else{
                    touse=i+j;
                }
            }
            if (count==2 && grid_buttons[touse].isEnabled()){
                grid_buttons[touse].setText(b);
                grid_buttons[touse].setEnabled(false);
                print=c;
                return 1;
            }
            count=0;
        }
        //for columns
        count=0;
        touse=0;
        for (int i=0;i<3;i++){
            for (int j=i;j<9;j+=3){
                if (grid_buttons[j].getText()==a){
                    count++;
                }
                else{
                    touse=j;
                }
            }
            if (count==2 && grid_buttons[touse].isEnabled()){
                grid_buttons[touse].setText(b);
                grid_buttons[touse].setEnabled(false);
                print=c;
                return 1;
            }
            count=0;
        }

        //Diagonals
        count=0;
        touse=0;
        for (int i=0;i<9;i+=4){
            if (grid_buttons[i].getText()==a){
                count++;
            }
            else{
                touse=i;
            }
        }
        if (count==2 && grid_buttons[touse].isEnabled()){
            grid_buttons[touse].setText(b);
            grid_buttons[touse].setEnabled(false);
            print=c;
            return 1;
        }
        count=0;
        touse=0;
        for (int i=2;i<7;i+=2){
            if (grid_buttons[i].getText()==a){
                count++;
            }
            else{
                touse=i;
            }
        }
        if (count==2 && grid_buttons[touse].isEnabled()){
            grid_buttons[touse].setText(b);
            grid_buttons[touse].setEnabled(false);
            print=c;
            return 1;
        }

        complete(2,button);
        return 1;
    }

    private int checkstate(){
        for (int i=0;i<9;i+=3){
            if (grid_buttons[i].getText()==grid_buttons[i+1].getText() && grid_buttons[i+2].getText()==grid_buttons[i+1].getText() && grid_buttons[i+1].getText()!=""){
                grid_buttons[i].setBackground(Color.blue);
                grid_buttons[i+1].setBackground(Color.blue);
                grid_buttons[i+2].setBackground(Color.blue);
                restart_popup(grid_buttons[i].getText());
                return 1;
            }
        }
        for (int i=0;i<3;i++){
            if (grid_buttons[i].getText()==grid_buttons[i+3].getText() && grid_buttons[i+6].getText()==grid_buttons[i+3].getText() && grid_buttons[i+3].getText()!=""){
                grid_buttons[i].setBackground(Color.blue);
                grid_buttons[i+3].setBackground(Color.blue);
                grid_buttons[i+6].setBackground(Color.blue);
                restart_popup(grid_buttons[i+3].getText());
                return 1;
            }
        }
        if (grid_buttons[0].getText()==grid_buttons[4].getText() && grid_buttons[4].getText()==grid_buttons[8].getText() && grid_buttons[4].getText()!=""){
            grid_buttons[0].setBackground(Color.blue);
            grid_buttons[4].setBackground(Color.blue);
            grid_buttons[8].setBackground(Color.blue);
            restart_popup(grid_buttons[0].getText());
            return 1;
        }
        else if (grid_buttons[4].getText()==grid_buttons[6].getText() && grid_buttons[4].getText()==grid_buttons[2].getText() && grid_buttons[2].getText()!=""){
            grid_buttons[2].setBackground(Color.blue);
            grid_buttons[4].setBackground(Color.blue);
            grid_buttons[6].setBackground(Color.blue);
            restart_popup(grid_buttons[4].getText());
            return 1;
        }
        else{
            int count=0;
            for (int i=0;i<9;i++){
                if (grid_buttons[i].getText()==""){
                    count++;
                    break;
                }

            }
            if (count==0){
                restart_popup("D");
            }
        }
        return 0;
    }
}
