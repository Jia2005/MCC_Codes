import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.geom.Path2D; 
import java.awt.geom.Point2D; 
import static java.lang.Math.*; 
 
class Hexagon { 
    private final JPanel parent; 
    private final double x; 
    private final double y; 
    private final double length; 
    private Color color; 
    private final String tags; 
    private Path2D.Double path; 
 
    public Hexagon(JPanel parent, double x, double y, double length, Color color, String tags) 
{ 
        this.parent = parent; 
        this.x = x; 
        this.y = y; 
        this.length = length; 
        this.color = color; 
        this.tags = tags; 
        drawHex(); 
    } 
 
    private void drawHex() { 
        double startX = x; 
        double startY = y; 
        double angle = 60; 
        path = new Path2D.Double(); 
        double firstX = startX + length * cos(toRadians(0)); 
        double firstY = startY + length * sin(toRadians(0)); 
        path.moveTo(firstX, firstY); 
         
        for (int i = 1; i <= 6; i++) { 
            double endX = startX + length * cos(toRadians(angle * i)); 
            double endY = startY + length * sin(toRadians(angle * i)); 
            path.lineTo(endX, endY); 
        } 
        path.closePath(); 
    } 
 
    public boolean contains(Point2D point) { 
        return path.contains(point); 
    } 
 
    public Point2D getCenter() { 
        return new Point2D.Double(x + 15, y + 25); 
    } 
 
    public Path2D.Double getPath() { 
        return path; 
    } 
 
    public Color getColor() { 
        return color; 
    } 
 
    public void setColor(Color color) { 
        this.color = color; 
    } 
 
    public String getTags() { 
        return tags; 
    } 
} 
 
class HexagonalGrid extends JPanel { 
    private static final int CANVAS_WIDTH = 800; 
    private static final int CANVAS_HEIGHT = 650; 
    private static final Point TOP_LEFT = new Point(20, 20); 
    private static final Point BOTTOM_LEFT = new Point(790, 560); 
    private static final Point TOP_RIGHT = new Point(780, 20); 
    private static final Point BOTTOM_RIGHT = new Point(780, 560); 
 
    private final java.util.List<Hexagon> hexagons = new java.util.ArrayList<>(); 
    private final int clusterSize; 
    private final int edgeLength; 
    private double hexRadius; 
    private double centerDist; 
    private double reuseDistance; 
    private boolean firstClick = true; 
    private int currentAngle = 330; 
    private final java.util.List<Point2D> coCellEndpoints = new java.util.ArrayList<>(); 
    private final java.util.List<Integer> reuseList = new java.util.ArrayList<>(); 
    private JLabel statusLabel; 
    private int currentCount = 0; 
    private int i, j; 
 
    public HexagonalGrid(int clusterSize, int edgeLength, int i, int j) { 
        this.clusterSize = clusterSize; 
        this.edgeLength = edgeLength; 
        this.i = i; 
        this.j = j; 
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT)); 
        setBackground(new Color(77, 208, 225)); 
        createGrid(16, 10); 
        calculateClusterReuse(); 
        setupMouseListener(); 
        setupStatusLabel(); 
    } 
 
    private void setupStatusLabel() { 
        statusLabel = new JLabel("Select a Hexagon", SwingConstants.CENTER); 
        statusLabel.setFont(new Font("Helvetica", Font.PLAIN, 12)); 
        statusLabel.setPreferredSize(new Dimension(CANVAS_WIDTH, 30)); 
    } 
 
    private void createGrid(int columns, int rows) { 
        double size = edgeLength; 
        for (int c = 0; c < columns; c++) { 
            double offset = (c % 2 == 0) ? 0 : size * sqrt(3) / 2; 
            for (int r = 0; r < rows; r++) { 
                double x = c * (edgeLength * 1.5) + 50; 
                double y = (r * (edgeLength * sqrt(3))) + offset + 15; 
                hexagons.add(new Hexagon(this, x, y, edgeLength, Color.WHITE, String.format("%d,%d", r, c))); 
            } 
        } 
    } 
 
    private void calculateClusterReuse() { 
        hexRadius = sqrt(3) / 2 * edgeLength; 
        centerDist = sqrt(3) * hexRadius; 
        reuseDistance = hexRadius * sqrt(3 * clusterSize); 
    } 
 
    private void setupMouseListener() { 
        addMouseListener(new MouseAdapter() { 
            @Override 
            public void mouseClicked(MouseEvent e) { 
                handleClick(e.getPoint()); 
                repaint(); 
            } 
        }); 
    } 
 
    private void handleClick(Point point) { 
        Hexagon clickedHexagon = null; 
        for (Hexagon hex : hexagons) { 
            if (hex.contains(point)) { 
                clickedHexagon = hex; 
                break; 
            } 
        } 
 
        if (clickedHexagon == null) return; 
 
        if (firstClick) { 
            firstClick = false; 
            statusLabel.setText("Now, select another hexagon that should be a co-cell of the original hexagon."); 
            clickedHexagon.setColor(Color.GREEN); 
            Point2D center = clickedHexagon.getCenter(); 
            coCellEndpoints.add(center); 
            calculateCoCells(center); 
        } else { 
            boolean isCoCell = false; 
            for (int index : reuseList) { 
                if (hexagons.get(index) == clickedHexagon) { 
                    isCoCell = true; 
                    break; 
                } 
            } 
 
            if (isCoCell) { 
                clickedHexagon.setColor(Color.GREEN); 
                statusLabel.setText("Correct! Cell " + clickedHexagon.getTags() + " is a co-cell."); 
                currentCount++; 
                if (currentCount == reuseList.size()) { 
                    statusLabel.setText("Great! Press Shift-R to restart"); 
                    drawCoChannelLines(); 
                } 
            } else { 
                clickedHexagon.setColor(Color.RED); 
                statusLabel.setText("Incorrect! Cell " + clickedHexagon.getTags() + " is not a co-cell."); 
            } 
        } 
    } 
 
    private void calculateCoCells(Point2D center) { 
        double startX = center.getX(); 
        double startY = center.getY(); 
         
        for (int angle = 0; angle < 360; angle += 60) { 
            double endX = startX + centerDist * i * cos(toRadians(angle)); 
            double endY = startY + centerDist * i * sin(toRadians(angle)); 
             
            if (j > 0) { 
                endX += centerDist * j * cos(toRadians(angle - 60)); 
                endY += centerDist * j * sin(toRadians(angle - 60)); 
            } 
             
            Hexagon closest = findClosestHexagon(new Point2D.Double(endX, endY)); 
            if (closest != null) { 
                reuseList.add(hexagons.indexOf(closest)); 
                coCellEndpoints.add(closest.getCenter()); 
            } 
        } 
    } 
 
    private Hexagon findClosestHexagon(Point2D point) { 
        Hexagon closest = null; 
        double minDistance = Double.MAX_VALUE; 
         
        for (Hexagon hex : hexagons) { 
            double distance = point.distance(hex.getCenter()); 
            if (distance < minDistance) { 
                minDistance = distance; 
                closest = hex; 
            } 
        } 
         
        return closest; 
    } 
 
    private void drawCoChannelLines() { 
        repaint(); 
    } 
 
    @Override 
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
 
        for (Hexagon hex : hexagons) { 
            g2d.setColor(hex.getColor()); 
            g2d.fill(hex.getPath()); 
            g2d.setColor(Color.BLACK); 
            g2d.draw(hex.getPath()); 
        } 
 
        if (currentCount == reuseList.size() && !coCellEndpoints.isEmpty()) { 
            g2d.setColor(Color.BLUE); 
            g2d.setStroke(new BasicStroke(2)); 
            Point2D first = coCellEndpoints.get(0); 
            for (int i = 1; i < coCellEndpoints.size(); i++) { 
                Point2D next = coCellEndpoints.get(i); 
                g2d.drawLine((int)first.getX(), (int)first.getY(), (int)next.getX(), (int)next.getY()); 
            } 
        } 
    } 
 
    public JLabel getStatusLabel() { 
        return statusLabel; 
    } 
} 
 
public class FrequencyReuseApp extends JFrame { 
    private final HexagonalGrid grid; 
 
    public FrequencyReuseApp(int i, int j) { 
        if (i == 0 && j == 0) { 
            throw new IllegalArgumentException("i & j both cannot be zero"); 
        } 
        if (j > i) { 
            throw new IllegalArgumentException("value of j cannot be greater than i"); 
        } 
 
        int N = i * i + i * j + j * j; 
        System.out.println("N is " + N); 
 
        setTitle("Frequency reuse and co-channel selection"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
        grid = new HexagonalGrid(N, 30, i, j); 
        setLayout(new BorderLayout()); 
        add(grid, BorderLayout.CENTER); 
        add(grid.getStatusLabel(), BorderLayout.SOUTH); 
 
        pack(); 
        setLocationRelativeTo(null); 
    } 
 
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> { 
            try { 
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
 
            String iStr = JOptionPane.showInputDialog("Enter i value:\nCommon (i,j) values are:\n(1,0), (1,1), (2,0), (2,1), (3,0), (2,2)", "1"); 
            String jStr = JOptionPane.showInputDialog("Enter j value:", "0"); 
 
            if (iStr != null && jStr != null) { 
                try { 
                    int i = Integer.parseInt(iStr); 
                    int j = Integer.parseInt(jStr); 
                    new FrequencyReuseApp(i, j).setVisible(true); 
                } catch (NumberFormatException e) { 
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE); 
                } catch (IllegalArgumentException e) { 
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
                } 
            } 
        }); 
    } 
} 
