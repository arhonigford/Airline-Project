import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ReservationClient {
    private static String GUIfirstName;
    private static String GUIlastName;
    private static int GUIage;
    private static String GIUairline;
    /**
     * Determines whether or not the specified {@code String} is parsable as a non-negative {@code int}.
     *
     * @param string the {@code String} to be used in the operation
     * @return {@code true}, if the specified {@code String} is parsable as a non-negative {@code int} and
     * {@code false} otherwise
     */
    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    } //isParsable

    /**
     * Connects to a {@code CountdownServer} instance and sends requests.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String hostname;
        String portString;
        int port;
        Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;
        String request;
        String response;

        try {
            hostname = JOptionPane.showInputDialog(null,
                    "What is the hostname you'd like to connect to?",
                    "Hostname?", JOptionPane.PLAIN_MESSAGE);

            if (hostname == null) {
                System.out.println();

                System.out.println("Goodbye!");

                return;
            } //end if


            portString = JOptionPane.showInputDialog(null,
                    "What is the port you'd like to connect to?",
                    "Port?", JOptionPane.PLAIN_MESSAGE);

            if (portString == null) {
                System.out.println();

                System.out.println("Goodbye!");

                return;
            } else if (!isParsable(portString)) {
                System.out.println();

                System.out.println("Error: the specified port must be a non-negative integer!");

                System.out.println();

                System.out.println("Goodbye!");

                return;
            } else {
                port = Integer.parseInt(portString);
            } //end if

            socket = new Socket(hostname, port);

            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            SwingUtilities.invokeAndWait(new CustomPanel());


            //I think threads here

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                userInputReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch

            if (socketWriter != null) {
                try {
                    socketWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //end try catch
            } //end if

            if (socketReader != null) {
                try {
                    socketReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //end try catch
            } //end if
        } //end try catch finally
        System.out.println(GUIage);
        System.out.println(GUIfirstName);
        System.out.println(GUIfirstName);
        System.out.println(GIUairline);
    }//
    static class CustomPanel extends JPanel implements Runnable{
        private CustomPanel contentPane;
        private JFrame frame;
        private static String airline;
        private BufferedImage image;

        public CustomPanel() {

            frame = new JFrame("Purdue University Flight Reservation System");

            FileManager fm;
            try {
                setOpaque(true);
                try {
                    image = ImageIO.read(new URL(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Purdue_Boilermakers_logo.svg/1200px-Purdue_Boilermakers_logo.svg.png"));
                } catch (IOException ioe) {
                    System.out.println("Unable to fetch image.");
                    ioe.printStackTrace();
                }
                Image img = getScaledImage(image, 300, 160);
                JLabel image1 = new JLabel(new ImageIcon(img));
                image1.setBounds(getWidth() / 2, getHeight() / 2, 300, 200);

                JLabel label1 = new JLabel("Welcome to the Purdue University Airline Reservation Management System!");
                JButton exit = new JButton("Exit");
                JButton book = new JButton("Book a Flight");
                JPanel main = new JPanel();
                BoxLayout bl = new BoxLayout(main, BoxLayout.Y_AXIS);
                main.add(Box.createVerticalStrut(30));
                main.setLayout(bl);
                main.add(label1);
                main.add(Box.createVerticalStrut(70));
                JPanel bottom = new JPanel();
                BoxLayout bot = new BoxLayout(bottom, BoxLayout.X_AXIS);
                bottom.setLayout(bot);
                bottom.add(Box.createHorizontalStrut(50));
                bottom.add(exit);
                bottom.add(Box.createHorizontalGlue());
                bottom.add(book);
                bottom.add(Box.createHorizontalStrut(50));
                main.add(image1);
                main.add(Box.createVerticalStrut(70));
                main.add(bottom);

                add(main);
                main.setVisible(true);


                JLabel label2 = new JLabel("Do you want to book a flight today?");
                JButton confirm = new JButton("Yes, I want to book a flight.");

                JButton exit1 = new JButton("Exit");
                exit1.setPreferredSize(new Dimension(100,50));
                JPanel main1 = new JPanel();
                BorderLayout main1Layout = new BorderLayout();
                main1.setLayout(main1Layout);
                Font font = new Font("serif", Font.BOLD, 30);
                label2.setFont(font);
                main1Layout.setVgap(150);
                main1.add(label2, BorderLayout.PAGE_START);
                main1Layout.setVgap(300);
                main1.add(confirm, BorderLayout.LINE_END);
                main1.add(exit1, BorderLayout.LINE_START);
                add(main1);
                main1.setVisible(false);

                String[] airlines = {"Alaska", "Delta", "Southwest"};
                JLabel label3 = new JLabel("Choose a flight from the drop down menu:");
                JButton exit2 = new JButton("Exit");
                exit2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.exit(0);
                    }
                });
                JButton choose = new JButton("Choose this flight");
                JPanel main2 = new JPanel();
                BorderLayout main2Layout = new BorderLayout();
                main2.setLayout(main2Layout);
                JPanel title1 = new JPanel();
                main2.add(title1, BorderLayout.PAGE_START);
                main2Layout.setVgap(20);
                JComboBox airlineList = new JComboBox(airlines);

                airlineList.setPrototypeDisplayValue("XXXXX");
                airlineList.setSelectedIndex(0);




                JPanel title2 = new JPanel();
                label3.setFont(font);
                //TODO: this is the miniFrame for the airlineList  - one more copy paste for this miniFrame too
                fm  = new FileManager(new File("reservations.txt"));
                airlineList.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String tempAirline = (String) airlineList.getItemAt(airlineList.getSelectedIndex());
                        if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH)
                            if (tempAirline.equals("Alaska")) {
                                new MiniFrame(tempAirline + " Airlines", fm.getAlaska()).displayPopUp();
                            } else if (tempAirline.equals("Southwest")) {
                                new MiniFrame(tempAirline + " Airlines", fm.getSouthwest()).displayPopUp();
                            } else {
                                new MiniFrame(tempAirline + " Airlines", fm.getDelta()).displayPopUp();
                            }
                    }
                });
                // TO HERE
                Alaska a = new Alaska(fm.getAlaska());
                //set passenger numbers, etc
                Delta d = new Delta(fm.getDelta());
                Southwest sw = new Southwest(fm.getSouthwest());
                JLabel info = new JLabel("<html>" + a.getInfo() + "<br>" + a.getOffers() + "<br>" + a.getEndingMessage() + "</html>");
                JPanel info1 = new JPanel();
                info.setPreferredSize(new Dimension(300, 200));
                airline = "Alaska Airlines";
                airlineList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox comboBox = (JComboBox) e.getSource();
                        String command = (String) comboBox.getSelectedItem();
                        JTextArea textArea = new JTextArea("");
                        if (command.equals("Alaska")) {
                            info.setText("<html>" + a.getInfo() + "<br>" + a.getOffers() + "<br>" + a.getEndingMessage() + "</html>");
                            airline = "Alaska Airlines";
                            textArea.setText(a.getPassengerString());
                        } else if (command.equals("Delta")) {
                            info.setText("<html>" + d.getInfo() + "<br>" + d.getOffers() + "<br>" + d.getEndingMessage() + "</html>");
                            textArea.setText(d.getPassengerString());
                            airline = "Delta Airlines";
                        } else if (command.equals("Southwest")) {
                            info.setText("<html>" + sw.getInfo() + "<br>" + sw.getOffers() + "<br>" + sw.getEndingMessage() + "</html>");
                            textArea.setText(sw.getPassengerString());
                            airline = "Southwest Airlines";
                        }
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        scrollPane.setPreferredSize(new Dimension(300, 300));
                        //JDialog.showMessageDialog(null, scrollPane, "", JOptionPane.CLOSED_OPTION);
                    }
                });
                //TODO: here is the second mini frame part - for this to work the frame has to be defined in custom panel not display GUI
                frame.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                            if (!airline.equals("")) {
                                if (airline.equals("Delta Airlines"))
                                    new MiniFrame(airline, fm.getDelta()).displayPopUp();
                                else if (airline.equals("Southwest Airlines"))
                                    new MiniFrame(airline, fm.getSouthwest()).displayPopUp();
                                else
                                    new MiniFrame(airline, fm.getAlaska()).displayPopUp();
                            }
                            else {
                                new MiniFrame((String) airlineList.getItemAt(airlineList.getSelectedIndex()) +
                                        " Airlines", fm.getAlaska()).displayPopUp();
                            }
                        }
                    }
                });
                //END HERE

                BorderLayout firstPartLayout = new BorderLayout();
                firstPartLayout.setVgap(20);
                JPanel firstPart = new JPanel(firstPartLayout);
                firstPart.add(label3, BorderLayout.PAGE_START);
                firstPart.add(airlineList, BorderLayout.PAGE_END);
                main2.add(firstPart, BorderLayout.PAGE_START);
                main2.add(info, BorderLayout.CENTER);
                JPanel buttons = new JPanel(new FlowLayout());
                buttons.add(exit2);
                buttons.add(choose);
                main2.add(buttons, BorderLayout.PAGE_END);

                add(main2);






                JLabel firstName = new JLabel("What is your first name?");
                JTextField enterFirstName = new JTextField("");
                enterFirstName.setPreferredSize(new Dimension(300, 75));
                JLabel lastName = new JLabel("What is your last name?");
                JTextField enterLastName = new JTextField("");
                enterLastName.setPreferredSize(new Dimension(300, 75));
                JLabel age = new JLabel("What is your age?");
                JTextField enterAge = new JTextField("");
                enterAge.setPreferredSize(new Dimension(300, 75));
                BorderLayout informationLayout = new BorderLayout();
                informationLayout.setVgap(15);
                JPanel information = new JPanel(informationLayout);

                BorderLayout firstLastLayout = new BorderLayout();
                firstLastLayout.setVgap(10);
                JLabel infoTitle = new JLabel("Please input your information below.");
                Font font3 = new Font("serif", Font.PLAIN, 20);
                infoTitle.setFont(font3);
                JPanel firstLastNames = new JPanel(firstLastLayout);
                firstLastNames.add(infoTitle, BorderLayout.PAGE_START);
                firstLastNames.add(firstName, BorderLayout.CENTER);
                firstLastNames.add(enterFirstName, BorderLayout.PAGE_END);
                information.add(firstLastNames, BorderLayout.PAGE_START);

                BorderLayout lastAgeLayout = new BorderLayout();
                lastAgeLayout.setVgap(10);
                JPanel lastAgeStuff = new JPanel(lastAgeLayout);
                lastAgeStuff.add(lastName, BorderLayout.PAGE_START);
                lastAgeStuff.add(enterLastName, BorderLayout.CENTER);
                lastAgeStuff.add(age, BorderLayout.PAGE_END);

                JPanel endStuff = new JPanel();
                BorderLayout endStuffLayout = new BorderLayout();
                endStuff.setLayout(endStuffLayout);
                endStuff.add(enterAge, BorderLayout.PAGE_START);
                JPanel bottomInformation = new JPanel(new FlowLayout());
                JButton leave = new JButton("Exit");
                bottomInformation.add(leave);
                JButton next = new JButton("Next");
                bottomInformation.add(next);
                endStuff.add(bottomInformation, BorderLayout.PAGE_END);

                information.add(firstLastNames, BorderLayout.PAGE_START);
                information.add(lastAgeStuff, BorderLayout.CENTER);
                information.add(endStuff, BorderLayout.PAGE_END);

                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        String[] options = {"OK"};
                        String name = enterFirstName.getText() + " " + enterLastName.getText();
                        String age = enterAge.getText();

                        Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(name);
                        boolean b = m.find();
                        if (!b) {
                            JOptionPane.showOptionDialog(null, "The specific name is not " +
                                            "a valid name!", "Information Gathering",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.ERROR_MESSAGE, null, options, null);
                        } else {
                            try {
                                Integer.parseInt(age);

                                int allFieldsLookCorrect = JOptionPane.showConfirmDialog(null,
                                        "Are all the details entered correct?" +
                                                "\n" + "\n" +
                                                "The passengers name is " + name + " and their" +
                                                " age is " + age + ". \n If all the information above is correct, select the Yes" +
                                                " button below, otherwise, select the No button.",
                                        "Confirm", JOptionPane.YES_NO_OPTION);
                                if (allFieldsLookCorrect == JOptionPane.YES_OPTION) {
                                    GUIage = Integer.parseInt(age);
                                    GUIfirstName = enterFirstName.getText();
                                    GUIlastName = enterLastName.getText();
                                    switch(airline){
                                        case"Alaska Airlines":
                                            fm.writeAlaskaPassenger(new Passenger(GUIfirstName, GUIlastName, GUIage));
                                            //a.addPassenger(new Passenger(GUIfirstName, GUIlastName, GUIage));
                                            break;
                                        case"Delta Airlines":
                                            fm.writeDeltaPassenger(new Passenger(GUIfirstName, GUIlastName, GUIage));
                                           // d.addPassenger(new Passenger(GUIfirstName, GUIlastName, GUIage));
                                            break;
                                        case"Southwest Airlines":
                                            try {
                                                fm.writeSouthwestPassenger(new Passenger(GUIfirstName, GUIlastName, GUIage));
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                           // sw.addPassenger(new Passenger(GUIfirstName, GUIlastName, GUIage));
                                            break;

                                    }
                                    //TODO: below this up to the main.setVisible shows the last panel (add lasFrame.setvisible false)
                                    BorderLayout lastFrameLayout = new BorderLayout();
                                    JPanel lastFrame = new JPanel();
                                    lastFrameLayout.setVgap(7);
                                    lastFrame.setLayout(lastFrameLayout);
                                    JPanel titleInfo = new JPanel(new BorderLayout());
                                    Font font2 = new Font("serif", Font.BOLD, 20);
                                    JLabel line1 = new JLabel("Flight data displaying for " + airline);
                                    line1.setFont(font2);
                                    titleInfo.add(line1,BorderLayout.PAGE_START);
                                    JLabel line2 = new JLabel("Enjoy Your Flight");
                                    line2.setFont(font2);
                                    titleInfo.add(line2, BorderLayout.CENTER);
                                    JLabel line3 = new JLabel("Flight is now boarding at Gate" );
                                    line3.setFont(font2);
                                    titleInfo.add(line3, BorderLayout.PAGE_END);
                                    lastFrame.add(titleInfo, BorderLayout.PAGE_START);
                                    JPanel flightInfo = new JPanel();
                                    BoxLayout bLayout = new BoxLayout(flightInfo, BoxLayout.Y_AXIS);
                                    flightInfo.setLayout(bLayout);
                                    flightInfo.setPreferredSize(new Dimension(400,200));
                                    Border black = BorderFactory.createLineBorder(Color.BLACK);
                                    flightInfo.setBorder(black);
                                    JTextArea area = new JTextArea();
                                    area.setMaximumSize(new Dimension(400,200));
                                    flightInfo.setMaximumSize(new Dimension(400, 200));
                                    if (airline.equals("Delta Airlines")) {
                                        for (Passenger passenger : fm.getDelta()) {
                                            JLabel label = new JLabel(passenger.toString());
                                            flightInfo.add(label);
                                        }
                                    }
                                    else if (airline.equals("Southwest Airlines")) {
                                        for (Passenger passenger : fm.getSouthwest()) {
                                            JLabel label = new JLabel(passenger.toString());
                                            flightInfo.add(label);                                        }
                                    } else {
                                        for (Passenger passenger : fm.getAlaska()) {
                                            JLabel label = new JLabel(passenger.toString());
                                            flightInfo.add(label);                                        }
                                    }
                                    //a;lsjdflkas;dflkdslkjf;alkdsjfljds

                                    JPanel bottomPanel = new JPanel();
                                    BoxLayout boxLayout = new BoxLayout(bottomPanel, BoxLayout.Y_AXIS);
                                    bottomPanel.setLayout(boxLayout);
                                    JLabel dash = new JLabel("-------------------------------------------" +
                                            "------------------------------------");
                                    JLabel boardingPass = new JLabel("boarding pass");
                                    JLabel passFirstName = new JLabel("PASSENGER FIRST NAME: " + GUIfirstName);
                                    JLabel passLastName = new JLabel("PASSENGER LAST NAME: " + GUIlastName);
                                    JLabel passAge = new JLabel("PASSENGER AGE: " + GUIage );
                                    JLabel beginBoarding = new JLabel("You can now begin boarding at gate ");
                                    JLabel dash2 = new JLabel("-------------------------------------------" +
                                            "------------------------------------");
                                    bottomPanel.add(dash);
                                    bottomPanel.add(boardingPass);
                                    bottomPanel.add(passFirstName);
                                    bottomPanel.add(passLastName);
                                    bottomPanel.add(passAge);
                                    bottomPanel.add(beginBoarding);
                                    bottomPanel.add(dash2);
                                    JPanel buttonStuff = new JPanel(new FlowLayout());
                                    JButton exit4 = new JButton("Exit");
                                    buttonStuff.add(exit4);
                                    JButton refresh = new JButton("Refresh Flight Status");
                                    buttonStuff.add(refresh);
                                    bottomPanel.add(buttonStuff);
                                    lastFrame.add(bottomPanel, BorderLayout.PAGE_END);
                                    JScrollPane scroll = new JScrollPane();
                                    //getContentPane().add(scroll);
                                    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                                    scroll.getViewport().add(flightInfo);
                                    scroll.setPreferredSize(new Dimension(380, 180));
                                    scroll.getViewport().add(flightInfo);
                                    lastFrame.add(scroll, BorderLayout.CENTER);
                                    lastFrame.setVisible(false);
                                    add(lastFrame);
                                    exit4.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent actionEvent) {
                                            System.exit(0);
                                        }
                                    });
                                    //TO HERE


                                    main.setVisible(false);
                                    main1.setVisible(false);
                                    main2.setVisible(false);
                                    information.setVisible(false);
                                    lastFrame.setVisible(true);


                                    //add in move to next page right here

                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showOptionDialog(null, "The specific age is not " +
                                                "a valid number!", "Information Gathering",
                                        JOptionPane.YES_NO_CANCEL_OPTION,
                                        JOptionPane.ERROR_MESSAGE, null, options, null);
                            }
                        }


                    }
                });

                add(information);

                main.setVisible(true);
                main2.setVisible(false);
                main1.setVisible(false);
                information.setVisible(false);
                book.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        main.setVisible(false);
                        main2.setVisible(false);
                        main1.setVisible(true);
                    }
                });

                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.setVisible(false);
                        main1.setVisible(false);
                        main2.setVisible(true);
                    }
                });
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        return;
                    }
                });

                exit1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                exit2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                JLabel label4 = new JLabel("Are you sure that you want to book a flight on " + airline + "?");
                JButton yes = new JButton("Yes, I want this flight.");
                JButton no = new JButton("No, I want a differnt flight.");
                JButton exit3 = new JButton("Exit");
                JPanel main4 = new JPanel();
                BoxLayout b4 = new BoxLayout(main4, BoxLayout.Y_AXIS);
                main4.setLayout(b4);
                main4.add(Box.createVerticalStrut(30));
                JPanel title3 = new JPanel();
                title3.setLayout(new BoxLayout(title3, BoxLayout.X_AXIS));
                title3.add(Box.createHorizontalStrut(50));
                title3.add(label4);
                title3.add(Box.createHorizontalStrut(50));
                main4.add(title3);
                main4.add(Box.createVerticalStrut(70));
                JPanel bottom3 = new JPanel();
                BoxLayout bot3 = new BoxLayout(bottom3, BoxLayout.X_AXIS);
                bottom3.setLayout(bot3);
                bottom3.add(Box.createHorizontalStrut(50));
                bottom3.add(exit3);
                bottom3.add(Box.createHorizontalStrut(50));
                bottom3.add(no);
                bottom3.add(Box.createHorizontalStrut(50));
                bottom3.add(yes);
                bottom3.add(Box.createHorizontalStrut(50));
                main4.add(Box.createVerticalStrut(200));
                main4.add(bottom3);
                add(main4);
                main4.setVisible(false);

                exit3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                choose.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.setVisible(false);
                        main1.setVisible(false);
                        main2.setVisible(false);
                        main4.setVisible(true);
                        String choice = (String) airlineList.getItemAt(airlineList.getSelectedIndex());
                        GIUairline = choice;
                    }
                });
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        main.setVisible(false);
                        main1.setVisible(false);
                        main2.setVisible(true);
                        main4.setVisible(false);
                    }
                });
                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        main.setVisible(false);
                        main1.setVisible(false);
                        main2.setVisible(false);
                        main4.setVisible(false);
                        information.setVisible(true);

                    }
                });


                add(information);
                information.setVisible(false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }




        }

        public String getAirline() {
            return airline;
        }


        private Image getScaledImage(Image srcImg, int w, int h) {
            BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(srcImg, 0, 0, w, h, null);
            g2.dispose();

            return resizedImg;
        }


        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(image.getWidth(), image.getHeight()));
        }


        public synchronized void displayGUI() {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setFocusable(true);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int height = screenSize.height * 2 / 3;
            int width = screenSize.width * 2 / 3;

            frame.setPreferredSize(new Dimension(width, height));

            contentPane = new ReservationClient.CustomPanel();

            frame.setContentPane(contentPane);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.pack();

        }

        @Override
        public void run() {
            displayGUI();
        }

        public void popUp(String airline) {
            JFrame popUp = new JFrame("Airline");
            JLabel title = new JLabel(airline + "Airlines");
            JButton exit = new JButton("Exit");
            popUp.setPreferredSize(new Dimension(300,300));
            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popUp.add(exit);
            popUp.add(title);
            popUp.pack();
            popUp.setVisible(true);


        }
    /*@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, getHeight()/7*4, getWidth()/6, image.getWidth()/4, image.getHeight()/4, this);
    }*/
    }


}
        /*
            while (request != null) {
                socketWriter.write(request);
                socketWriter.newLine();
                socketWriter.flush();
                response = socketReader.readLine();
                System.out.println();
                System.out.printf("Response from the server: %s%n", response);
                System.out.println();
                System.out.print("Enter your request: ");
                request = userInputReader.readLine();
            } //end while
            System.out.println();
            System.out.println("Goodbye!");
         */