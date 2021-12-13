package api;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MyFrame extends JFrame {
        DirectedWeightedGraphAlgorithms algo= new Graph_Algorithm();
        DirectedWeightedGraph graphDW= new Graph();
        private JMenuBar menuBar;
        private JMenu graph;
        private JMenu func;
        private JMenu load;
        private JMenu save;
        private JMenu clear;
        //***********************
        private JMenuItem isConnected;
        private JMenuItem shortestPathDist;
        private JMenuItem shortestPath;
        private JMenuItem center;
        private JMenuItem tsp;
//**************************************
        private JMenuItem getNode;
        private JMenuItem getEdge;
        private JMenuItem addNode;
        private JMenuItem connect;
        private JMenuItem removeNode;
        private JMenuItem removeEdge;
//*****************************************
        private MyPanel mp;

        public MyFrame(){
            algo.init(graphDW);
            algo.load("./Assignments/Ex2/data/G1.json");
            mp = new MyPanel();
//            this.mp.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
//            mp.setLayout(new GridLayout(0,1));
            this.add(mp);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("My_GUI");
            this.pack();
            initFrame();
            initPanel();
            addMenu();
            this.setVisible(true);
        }

        private void initFrame()
        {
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = screen.width;
            int h = screen.height;
            this.setSize(w/2,h/2);
        }


        private void addMenu() {
            menuBar = new JMenuBar();
            graph = new JMenu("Graph");

            getNode = new JMenuItem("getNode");
            getNode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.removeAll();
                    mp.repaint();
                    JLabel getNLabel = new JLabel("get node:");
                    getNLabel.setBounds(100, 100, 1000, 45);
                    mp.add(getNLabel);
                    JLabel idLabel = new JLabel("id:");
                    idLabel.setBounds(100, 180, 100, 45);
                    mp.add(idLabel);

                    JTextField idText = new JTextField();
                    idText.setBounds(120, 190, 165, 25);
                    mp.add(idText);

                    int id= Integer.parseInt(idText.getText());
                    JButton getNode= new JButton("Ok");
                    getNode.setBounds(200, 190,100,45);
                    mp.add(getNode);
                    getNode.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            graphDW.getNode((id));
                        }
                    });

                }
            });




            getEdge = new JMenuItem("getEdge");
            getEdge.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.removeAll();
                    mp.repaint();
                    JLabel getNLabel = new JLabel("get Edge function:");
                    getNLabel.setBounds(100, 100, 1000, 45);
                    mp.add(getNLabel);

                    JLabel srcLabel = new JLabel("src:");
                    srcLabel.setBounds(100, 180, 100, 45);
                    mp.add(srcLabel);
                    JTextField srcText = new JTextField();
                    srcText.setBounds(100, 190, 165, 25);
                    mp.add(srcText);

                    JLabel destLabel = new JLabel("dest:");
                    destLabel.setBounds(100, 180, 100, 45);
                    mp.add(destLabel);
                    JTextField destText = new JTextField();
                    destText.setBounds(120, 190, 165, 25);
                    mp.add(destText);

                    int src= Integer.parseInt(srcText.getText());
                    int dest= Integer.parseInt(destText.getText());
                    JButton EdgeB= new JButton("Ok");
                    EdgeB.setBounds(200, 190,100,45);
                    mp.add(EdgeB);
                    EdgeB.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            graphDW.getEdge(src,dest);
                        }
                    });
                    mp.add(EdgeB);
                }
            });


            addNode = new JMenuItem("Add node");
            addNode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.removeAll();
                    mp.repaint();
                    JLabel title = new JLabel("Add new node");
                    title.setBounds(100, 10, 100, 45);
                    mp.add(title);

                    JLabel Node_id = new JLabel("id:");
                    Node_id.setBounds(30, 40, 100, 45);
                    mp.add(Node_id);

                    JTextField Node = new JTextField();
                    Node.setBounds(80, 50, 165, 25);
                    mp.add(Node);

                    JLabel Node_location = new JLabel("location:");
                    Node_location.setBounds(30, 100, 100, 45);
                    mp.add(Node_location);

                    JLabel XLable = new JLabel("X:");
                    XLable.setBounds(100, 100, 100, 45);
                    mp.add(XLable);

                    JTextField XText = new JTextField();
                    XText.setBounds(120, 110, 165, 25);
                    mp.add(XText);

                    JLabel YLable = new JLabel("Y:");
                    YLable.setBounds(100, 140, 100, 45);
                    mp.add(YLable);

                    JTextField YText = new JTextField();
                    YText.setBounds(120, 150, 165, 25);
                    mp.add(YText);

                    int id= Integer.parseInt(Node.getText());
                    JButton addButton = new JButton("Ok");
                    addButton.setBounds(30, 220,80,25);
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            NodeData new_node = new Node_Data((id));
                            algo.getGraph().addNode(new_node);
                        }
                    });
                     mp.add(addButton);

                }
            });





            connect = new JMenuItem("Add edge");
            connect.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.removeAll();
                    mp.repaint();

                    JLabel title = new JLabel("Add an edge");
                    title.setBounds(100, 10, 100, 45);
                    mp.add(title);

                    JLabel src = new JLabel("src:");
                    src.setBounds(30, 40, 100, 45);
                    mp.add(src);

                    JTextField srcText = new JTextField();
                    srcText.setBounds(80, 50, 165, 25);
                    mp.add(srcText);

                    JLabel dest = new JLabel("dest:");
                    dest.setBounds(30, 70, 100, 45);
                    mp.add(dest);

                    JTextField destText = new JTextField();
                    destText.setBounds(80, 80, 165, 25);
                    mp.add(destText);

                    JLabel Edge_weight = new JLabel("weight:");
                    Edge_weight.setBounds(30, 100, 100, 45);
                    mp.add(Edge_weight);

                    JTextField weightText = new JTextField();
                    weightText.setBounds(100, 110, 165, 25);
                    mp.add(weightText);

                    JButton addButton = new JButton("Add");
                    addButton.setBounds(30, 140,80,25);
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            algo.getGraph().connect(Integer.parseInt(srcText.getText()),
                                    Integer.parseInt(destText.getText()),
                                    Double.parseDouble(weightText.getText()));

                        }
                    });
                    mp.add(addButton);

                }
            });


            removeNode = new JMenuItem("removeNode");
            removeNode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.removeAll();
                    mp.repaint();

                    JLabel title = new JLabel("Remove Node");
                    title.setBounds(100, 10, 100, 45);
                    mp.add(title);

                    JLabel id = new JLabel("id:");
                    id.setBounds(30, 40, 100, 45);
                    mp.add(id);

                    JTextField keyText = new JTextField();
                    keyText.setBounds(80, 50, 165, 25);
                    mp.add(keyText);

                    JButton removeButton = new JButton("Remove");
                    removeButton.setBounds(30, 80,100,25);
                    removeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            algo.getGraph().removeNode(Integer.parseInt(keyText.getText()));
                        }
                    });
                    mp.add(removeButton);

                }
            });


            removeEdge = new JMenuItem("removeEdge");
            removeEdge.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.removeAll();
                    mp.repaint();
                    JLabel title = new JLabel("Remove an edge");
                    title.setBounds(100, 10, 100, 45);
                    mp.add(title);

                    JLabel src_Edge = new JLabel("src:");
                    src_Edge.setBounds(30, 40, 100, 45);
                    mp.add(src_Edge);

                    JTextField srcText = new JTextField();
                    srcText.setBounds(80, 50, 165, 25);
                    mp.add(srcText);

                    JLabel Edge_dest = new JLabel("dest:");
                    Edge_dest.setBounds(30, 70, 100, 45);
                    mp.add(Edge_dest);

                    JTextField destText = new JTextField();
                    destText.setBounds(80, 80, 165, 25);
                    mp.add(destText);

                    JButton removeButton = new JButton("Remove");
                    removeButton.setBounds(30, 140,100,25);
                    removeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            algo.getGraph().removeEdge(Integer.parseInt(srcText.getText()),
                                    Integer.parseInt(destText.getText()));

                        }
                    });
                    mp.add(removeButton);

                }
            });


//**********************************************************************************************************


            func= new JMenu("functions");

            isConnected = new JMenuItem("isConnected");//************
            isConnected.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.repaint();
                JLabel jl= new JLabel(String.valueOf(algo.isConnected()));
                jl.setBounds(100,200,100,200);
                mp.add(jl);

                }
            });
            shortestPathDist = new JMenuItem("shortestPathDist"); //************
            shortestPathDist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.repaint();
                }
            });
            shortestPath = new JMenuItem("shortestPath");//************
            shortestPath.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.repaint();
                    int x=0;//getsrc
                    int y=0;//getDest
                    JLabel jl= new JLabel(String.valueOf(algo.shortestPath(x,y)));
                    jl.setBounds(100,200,100,200);
                    mp.add(jl);

                }
            });
            center = new JMenuItem("center");//************
            center.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.repaint();
                    JLabel jl= new JLabel(String.valueOf(algo.center()));
                    jl.setBounds(100,200,100,200);
                    mp.add(jl);

                }
            });
            tsp = new JMenuItem("tsp");//******************************************************
            tsp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.repaint();

                }
            });

            load = new JMenu("load");//********************
            save = new JMenu("save");//********************
            clear= new JMenu("clear");

            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp.repaint();
                }
            });
            func.add(isConnected);
            func.add(shortestPathDist);
            func.add(shortestPath);
            func.add(center);
            func.add(tsp);
            //***********************
            graph.add(getNode);
            graph.add(getEdge);
            graph.add(addNode);
            graph.add(connect);
            graph.add(removeNode);
            graph.add(removeEdge);
            //**********************
            menuBar.add(graph);
            menuBar.add(func);
            menuBar.add(load);
            menuBar.add(save);
            menuBar.add(clear);
            this.setJMenuBar(menuBar);

        }
        private void initPanel(){
            MyPanel myPanel = new MyPanel();
            this.add(myPanel);
        }

    public static void main(String[] args) {
       MyFrame f= new MyFrame();
    }
}


