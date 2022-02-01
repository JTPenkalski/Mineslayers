package com.mineslayers.ui;

import java.io.IOException;
import java.util.ArrayList;
import com.mineslayers.entity.BoardTileEvent;
import com.mineslayers.entity.BoardTileEventType;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.event.MouseEventArgs;
import com.mineslayers.id.ImageID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class BoardTile extends StackPane implements MessageInvoker<BoardTile, MouseEventArgs>
{
    private boolean visited;
    private int neighboringEnemies;
    private int neighboringItems;
    private int neighboringMerchants;
    private int x;
    private int y;
    private BoardTileEvent event;
    private ArrayList<MessageObserver<BoardTile, MouseEventArgs>> observers;
    @FXML private ImageView baseImageView;
    @FXML private ImageView detailImageView;
    @FXML private ImageView mouseInteractionImageView;
    @FXML private Label neighboringEnemiesLabel;
    @FXML private Label neighboringItemsLabel;

    public BoardTile(int x, int y, Image baseImage) { this(x, y, baseImage, null); }

    public BoardTile(int x, int y, Image baseImage, Image detailImage)
    {
        // Initialize fields
        visited = false;
        neighboringEnemies = 0;
        neighboringMerchants = 0;
        neighboringItems = 0;
        this.x = x;
        this.y = y;
        event = null;
        observers = new ArrayList<>();

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/BoardTile.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            fxmlLoader.load();
        }
        catch (IOException ex)
        {
            System.out.println("Error creating BoardTile from FXML file!");

            // If the FXML file fails, create the class normally
            baseImageView = new ImageView();
            detailImageView = new ImageView();
            neighboringEnemiesLabel = new Label();
            neighboringItemsLabel = new Label();
            mouseInteractionImageView = new ImageView();

            getChildren().addAll(baseImageView, detailImageView, mouseInteractionImageView);
            initialize();
        }

        // Setup images
        setBaseImage(baseImage);
        setDetailImage(detailImage);
    }

    @FXML
    private void initialize()
    {
        // Configure JavaFX properties
        mouseInteractionImageView.setOpacity(0.25);
        getStyleClass().add("board-tile");

        // Setup JavaFX event system
        addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            boolean rightClick = e.getButton() == MouseButton.SECONDARY;

            if (!rightClick && event != null && !visited)
                event.invoke();

            invoke(this, new MouseEventArgs(rightClick));
        });

        addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
        {
            setMouseInteractionImage(ImageID.WHITE);
        });

        addEventHandler(MouseEvent.MOUSE_EXITED, e ->
        {
            setMouseInteractionImage(null);
        });

        addEventHandler(MouseEvent.MOUSE_PRESSED, e ->
        {
            setMouseInteractionImage(ImageID.BLACK);
        });

        addEventHandler(MouseEvent.MOUSE_RELEASED, e ->
        {
            setMouseInteractionImage(null);
        });
    }

    @Override
    public void addObserver(MessageObserver<BoardTile, MouseEventArgs> observer) { observers.add(observer); }

    @Override
    public void invoke(BoardTile sender, MouseEventArgs eventArgs)
    {
        for (var o : observers)
            o.onEvent(sender, eventArgs);
    }

    @Override
    public void removeObserver(MessageObserver<BoardTile, MouseEventArgs> observer) { observers.remove(observer); }

    public void setBaseImage(Image baseImage) { baseImageView.setImage(baseImage); }

    public void setDetailImage(Image detailImage) { detailImageView.setImage(detailImage); }

    public void setEvent(BoardTileEvent event) { this.event = event; }

    public void setNeighboringEnemies(int neighboringEnemies)
    {
        this.neighboringEnemies = neighboringEnemies;
        neighboringEnemiesLabel.setText(neighboringEnemies + "");
    }

    public void setNeighboringItems(int neighboringItems)
    {
        this.neighboringItems = neighboringItems;
        neighboringItemsLabel.setText(neighboringItems + "");
    }

    public void setNeighboringMerchants(int neighboringMerchants) { /* TODO */ }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
        setBaseImage(ImageID.STONE);

        if (event == null)
        {
            setDetailImage(null);
        }
        else
        {
            if (event.getType() == BoardTileEventType.ENEMY)
            {
                setBaseImage(ImageID.GRASS);
                setDetailImage(ImageID.FLAG_BOARD);
            }
        }
    }

    public boolean isVisited() { return visited; }

    public int getNeighboringEnemies() { return neighboringEnemies; }

    public int getNeighboringItems() { return neighboringItems; }

    public int getNeighboringMerchants() { return neighboringMerchants; }

    public int getTotalNeighbors() { return getNeighboringEnemies() + getNeighboringMerchants() + getNeighboringItems(); }

    public int getX() { return x; }

    public int getY() { return y; }

    public BoardTileEvent getEvent() { return event; }

    private void setMouseInteractionImage(Image mouseInteractionImage) { mouseInteractionImageView.setImage(mouseInteractionImage); }
}