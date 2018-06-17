package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.dvb.ui.DVBColor;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import org.havi.ui.HScreen;
import org.havi.ui.HScreenDimension;
import org.havi.ui.HScreenPoint;
import org.havi.ui.HStaticText;
import org.havi.ui.HStillImageBackgroundConfiguration;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;


public class HelloTVXlet implements Xlet, HActionListener, HBackgroundImageListener, ResourceClient {
    HScene scene;
    HStaticText text1 = new HStaticText("Hotel Television Service", 50, 20, 620, 100);
    HTextButton button1  = new HTextButton("General information",50,440,300,50);
    HTextButton button2  = new HTextButton("Serving times",50,500,300,50);
    HTextButton button3  = new HTextButton("Order breakfast and drinks",400,440,300,50);
    HTextButton button4  = new HTextButton("Call cleaning services",400,500,300,50);
    HTextButton buttonReturn  = new HTextButton("Back",50,500,100,50); 
    HStaticText text2 = new HStaticText("Welcome to the Grand Hiat Hotel \n \n To access television please contact the lobby, \n however, our information channel is available. \n \n If you have any other problems, \n  do not hesitate to contact the lobby.",50, 150, 620, 250);
    HStaticText text3 = new HStaticText("Breakfast is served from 7:30am until 10:30am \n \n Our restaurants open at 11:30am, \n they will continue serving lunch until 4:00pm  \n At 4pm the lunch menu will be changed for a dinner menu. \n Our restaurants close at 11:30pm.",50, 150, 620, 200);
    int breakfast = 0;
    int drinks = 0;
    HStaticText textBreakfast      = new HStaticText("Breakfast: €2,30",275, 150, 190, 50);
    HStaticText textAmountOfFood  = new HStaticText(Integer.toString(breakfast),345, 220, 50, 50);
    HTextButton buttonBreakfastSubstract   = new HTextButton("-",275, 220, 50, 50);
    HTextButton buttonBreakfastAdd   = new HTextButton("+",415, 220, 50, 50);
    HStaticText textDrinks      = new HStaticText("Drinks €1,70",275, 290, 190, 50);
    HStaticText textAmountOfDrinks  = new HStaticText(Integer.toString(drinks),345, 360, 50, 50);
    HTextButton buttonDrinksSubstract   = new HTextButton("-",275, 360, 50, 50);
    HTextButton buttonDrinksAdd   = new HTextButton("+",415, 360, 50, 50);
    HTextButton buttonOrder  = new HTextButton("Order",475,360,100,50);
    HStaticText textOrder = new HStaticText("Your order has been placed!",50, 200, 650, 200);
    double totalPrice = 0;
    double priceDrinks = 1.70;
    double priceBreakfast = 2.30;
    
    HTextButton buttonCleaning1 = new HTextButton("Fresh sheets and towels",400, 260, 300, 50);
    HTextButton buttonCleaning2 = new HTextButton("Light cleaning",400, 320, 300, 50);
    HTextButton buttonCleaning3 = new HTextButton("Full cleaning",400, 380, 300, 50);
    HBackgroundImage image = new HBackgroundImage("hotel.jpg");
    HStillImageBackgroundConfiguration hsbconfig;
    
    public void initXlet(XletContext context) {
      HScreen screen=HScreen.getDefaultHScreen();
      HBackgroundDevice hbgDev=screen.getDefaultHBackgroundDevice();
      if (hbgDev.reserveDevice(this))
      {
          System.out.println("Background device succesfully reserved");
      }
      HBackgroundConfigTemplate bgTemplate=new HBackgroundConfigTemplate();
      bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
      hsbconfig=(HStillImageBackgroundConfiguration) hbgDev.getBestConfiguration(bgTemplate);
    
      try {
         hbgDev.setBackgroundConfiguration(hsbconfig); 
      } catch(Exception ex){
          ex.printStackTrace();
      } 
      
      image.load(this);
      
      scene = HSceneFactory.getInstance().getDefaultHScene();
      
      text1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      text1.setBackground(new DVBColor(91, 68, 62, 255));
      text1.setForeground(new DVBColor(0, 0, 0, 255));
      scene.add(text1);
      
      button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button1.setBackground(Color.GREEN);
      scene.add(button1);
      
      button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button2.setBackground(Color.GREEN);
      scene.add(button2);
      
      button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button3.setBackground(Color.GREEN);
      scene.add(button3);
      
      button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      button4.setBackground(Color.GREEN);
      scene.add(button4);
      
      button1.setFocusTraversal(button2,button2,button3,button3);
      button2.setFocusTraversal(button1,button1,button4,button4);
      button3.setFocusTraversal(button4,button4,button1,button1);
      button4.setFocusTraversal(button3,button3,button2,button2);
      
      button1.setActionCommand("button1");
      button2.setActionCommand("button2");
      button3.setActionCommand("button3");
      button4.setActionCommand("button4");
      buttonReturn.setActionCommand("buttonReturn");
      buttonBreakfastSubstract.setActionCommand("buttonBreakfastSubstract");
      buttonBreakfastAdd.setActionCommand("buttonBreakfastAdd");
      buttonDrinksSubstract.setActionCommand("buttonDrinksSubstract");
      buttonDrinksAdd.setActionCommand("buttonDrinksAdd");
      buttonOrder.setActionCommand("buttonOrder");
      buttonCleaning1.setActionCommand("buttonCleaning1");
      buttonCleaning2.setActionCommand("buttonCleaning2");
      buttonCleaning3.setActionCommand("buttonCleaning3");
      
      button1.addHActionListener(this);
      button2.addHActionListener(this);
      button3.addHActionListener(this);
      button4.addHActionListener(this);
      buttonReturn.addHActionListener(this);
      buttonBreakfastSubstract.addHActionListener(this);
      buttonBreakfastAdd.addHActionListener(this);
      buttonDrinksSubstract.addHActionListener(this);
      buttonDrinksAdd.addHActionListener(this);
      buttonOrder.addHActionListener(this);
      buttonCleaning1.addHActionListener(this);
      buttonCleaning2.addHActionListener(this);
      buttonCleaning3.addHActionListener(this);
      
      button1.requestFocus();
    }


    
    public void startXlet() {
        scene.validate();
        scene.setVisible(true);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
          
        if(arg0.getActionCommand().equals("button1")) { 
            scene.remove(button1);
            scene.remove(button2);
            scene.remove(button3);
            scene.remove(button4);
            scene.remove(textBreakfast);
            scene.remove(textAmountOfFood);
            scene.remove(buttonBreakfastSubstract);
            scene.remove(buttonBreakfastAdd);
            scene.remove(textDrinks);
            scene.remove(textAmountOfDrinks);
            scene.remove(buttonDrinksSubstract);
            scene.remove(buttonDrinksAdd);
            scene.remove(buttonOrder);
            scene.remove(textOrder);
            scene.remove(buttonCleaning1);
            scene.remove(buttonCleaning2);
            scene.remove(buttonCleaning3);
            
            buttonReturn.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonReturn.setBackground(Color.ORANGE);
            scene.add(buttonReturn);
            
            text2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            text2.setBackground(new DVBColor(91, 68, 62, 255));
            text2.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(text2);
            
            buttonReturn.requestFocus();
            scene.repaint();
        }
        
        if(arg0.getActionCommand().equals("button2")) {
            scene.remove(button1);
            scene.remove(button2);
            scene.remove(button3);
            scene.remove(button4);
            scene.remove(textBreakfast);
            scene.remove(textAmountOfFood);
            scene.remove(buttonBreakfastSubstract);
            scene.remove(buttonBreakfastAdd);
            scene.remove(textDrinks);
            scene.remove(textAmountOfDrinks);
            scene.remove(buttonDrinksSubstract);
            scene.remove(buttonDrinksAdd);
            scene.remove(buttonOrder);
            scene.remove(textOrder);
            scene.remove(buttonCleaning1);
            scene.remove(buttonCleaning2);
            scene.remove(buttonCleaning3);
            
            buttonReturn.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonReturn.setBackground(Color.ORANGE);
            scene.add(buttonReturn);
            
            text3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            text3.setBackground(new DVBColor(91, 68, 62, 255));
            text3.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(text3);
            
            buttonReturn.requestFocus();
            scene.repaint();
        }
        
        if(arg0.getActionCommand().equals("button3")) {
            scene.remove(textOrder);
            scene.remove(buttonCleaning1);
            scene.remove(buttonCleaning2);
            scene.remove(buttonCleaning3);
            
            textBreakfast.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textBreakfast.setBackground(new DVBColor(91, 68, 62, 255));
            textBreakfast.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(textBreakfast);
            
            textAmountOfFood.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textAmountOfFood.setBackground(new DVBColor(91, 68, 62, 255));
            textAmountOfFood.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(textAmountOfFood);
            
            buttonBreakfastSubstract.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonBreakfastSubstract.setBackground(Color.GREEN);
            scene.add(buttonBreakfastSubstract);
            
            buttonBreakfastAdd.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonBreakfastAdd.setBackground(Color.GREEN);
            scene.add(buttonBreakfastAdd);
            
            textDrinks.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textDrinks.setBackground(new DVBColor(91, 68, 62, 255));
            textDrinks.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(textDrinks);
            
            textAmountOfDrinks.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textAmountOfDrinks.setBackground(new DVBColor(91, 68, 62, 255));
            textAmountOfDrinks.setForeground(new DVBColor(0, 0, 0, 255));
            scene.add(textAmountOfDrinks);
            
            buttonDrinksSubstract.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonDrinksSubstract.setBackground(Color.GREEN);
            scene.add(buttonDrinksSubstract);
            
            buttonDrinksAdd.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonDrinksAdd.setBackground(Color.GREEN);
            scene.add(buttonDrinksAdd);
            
            buttonOrder.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonOrder.setBackground(Color.ORANGE);
            scene.add(buttonOrder);
            
            buttonBreakfastSubstract.setFocusTraversal(null,buttonDrinksSubstract,buttonBreakfastAdd,buttonBreakfastAdd);
            buttonBreakfastAdd.setFocusTraversal(null,buttonDrinksAdd,buttonBreakfastSubstract,buttonBreakfastSubstract);
            buttonDrinksSubstract.setFocusTraversal(buttonBreakfastSubstract,button1,buttonOrder,buttonDrinksAdd);
            buttonDrinksAdd.setFocusTraversal(buttonBreakfastAdd,button3,buttonDrinksSubstract,buttonOrder);
            buttonOrder.setFocusTraversal(null, button3, buttonDrinksAdd, buttonDrinksSubstract);
            button1.setFocusTraversal(buttonDrinksSubstract,button2,button3,button3);
            button2.setFocusTraversal(button1,button1,button4,button4);
            button3.setFocusTraversal(buttonDrinksAdd,button4,button1,button1);
            button4.setFocusTraversal(button3,button3,button2,button2);
            
            buttonBreakfastSubstract.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("buttonBreakfastSubstract")) {
            if(breakfast > 0) {
                breakfast--;
                totalPrice -= priceBreakfast;
            }
            textAmountOfFood.setTextContent(Integer.toString(breakfast), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("buttonBreakfastAdd")) {
            breakfast++;
            totalPrice += priceBreakfast;
            textAmountOfFood.setTextContent(Integer.toString(breakfast), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("buttonDrinksSubstract")) {
            if(drinks > 0) {
                drinks--;
                totalPrice -= priceDrinks;
            }
            textAmountOfDrinks.setTextContent(Integer.toString(drinks), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("buttonDrinksAdd")) {
            drinks++;
            totalPrice += priceDrinks;
            textAmountOfDrinks.setTextContent(Integer.toString(drinks), HVisible.NORMAL_STATE);
        }
        if(arg0.getActionCommand().equals("button4")) { 
            scene.remove(textBreakfast);
            scene.remove(textAmountOfFood);
            scene.remove(buttonBreakfastSubstract);
            scene.remove(buttonBreakfastAdd);
            scene.remove(textDrinks);
            scene.remove(textAmountOfDrinks);
            scene.remove(buttonDrinksSubstract);
            scene.remove(buttonDrinksAdd);
            scene.remove(buttonOrder);
            scene.remove(textOrder);
            
            buttonCleaning1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonCleaning1.setBackground(new DVBColor(91, 68, 62, 255));
            buttonCleaning1.setForeground(Color.BLACK);
            scene.add(buttonCleaning1);
            
            buttonCleaning2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonCleaning2.setBackground(new DVBColor(91, 68, 62, 255));
            buttonCleaning2.setForeground(Color.BLACK);
            scene.add(buttonCleaning2);
            
            buttonCleaning3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            buttonCleaning3.setBackground(new DVBColor(91, 68, 62, 255));
            buttonCleaning3.setForeground(Color.BLACK);
            scene.add(buttonCleaning3);
            
            buttonCleaning1.setFocusTraversal(button4,buttonCleaning2,null,null);
            buttonCleaning2.setFocusTraversal(buttonCleaning1,buttonCleaning3,null,null);
            buttonCleaning3.setFocusTraversal(buttonCleaning2,button3,null,null);
            button1.setFocusTraversal(buttonCleaning3,button2,button3,button3);
            button2.setFocusTraversal(button1,button1,button4,button4);
            button3.setFocusTraversal(buttonCleaning3,button4,button1,button1);
            button4.setFocusTraversal(button3,buttonCleaning1,button2,button2);
            
            buttonCleaning1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("buttonCleaning1")) {
            scene.remove(buttonCleaning1);
            scene.remove(buttonCleaning2);
            scene.remove(buttonCleaning3);
            
            textOrder.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textOrder.setBackground(new DVBColor(91, 68, 62, 255));
            textOrder.setForeground(Color.BLACK);
            textOrder.setTextContent("Housekeeping notified." + "\n We will be right with you with \n fresh sheets and towels.", HVisible.NORMAL_STATE);
            scene.add(textOrder);
            
            button1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("buttonCleaning2")) {
            scene.remove(buttonCleaning1);
            scene.remove(buttonCleaning2);
            scene.remove(buttonCleaning3);
            
            textOrder.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textOrder.setBackground(new DVBColor(91, 68, 62, 255));
            textOrder.setForeground(Color.BLACK);
            textOrder.setTextContent("Housekeeping notified." + "\n We'll be right with you for \n some light cleaning", HVisible.NORMAL_STATE);
            scene.add(textOrder);
            
            button1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("buttonCleaning3")) {
            scene.remove(buttonCleaning1);
            scene.remove(buttonCleaning2);
            scene.remove(buttonCleaning3);
            
            textOrder.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textOrder.setBackground(new DVBColor(91, 68, 62, 255));
            textOrder.setForeground(Color.BLACK);
            textOrder.setTextContent("Housekeeping notified." + "\n We'll be right with you for \n a full cleaning", HVisible.NORMAL_STATE);
            scene.add(textOrder);
            
            button1.requestFocus();
            scene.repaint();
        }
        if(arg0.getActionCommand().equals("buttonReturn")) { 
            button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            button1.setBackground(Color.GREEN);
            scene.add(button1);

            button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            button2.setBackground(Color.GREEN);
            scene.add(button2);

            button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            button3.setBackground(Color.GREEN);
            scene.add(button3);

            button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            button4.setBackground(Color.GREEN);
            scene.add(button4);
            
            scene.remove(text2);
            scene.remove(text3);
            scene.remove(buttonReturn);
            
            button1.requestFocus();
            scene.repaint();
        }
        
        
        if(arg0.getActionCommand().equals("buttonOrder")) {
            scene.remove(textBreakfast);
            scene.remove(textAmountOfFood);
            scene.remove(buttonBreakfastSubstract);
            scene.remove(buttonBreakfastAdd);
            scene.remove(textDrinks);
            scene.remove(textAmountOfDrinks);
            scene.remove(buttonDrinksSubstract);
            scene.remove(buttonDrinksAdd);
            scene.remove(buttonOrder);
            
            textOrder.setBackgroundMode(HVisible.BACKGROUND_FILL);
            textOrder.setBackground(new DVBColor(91, 68, 62, 255));
            textOrder.setForeground(Color.BLACK);
            textOrder.setTextContent("Order placed. \n Your total: € " + Double.toString(totalPrice) + "\n Meals served on following day, \n see info.", HVisible.NORMAL_STATE);
            scene.add(textOrder);
            
            scene.repaint();
        }
    }

    public void imageLoaded(HBackgroundImageEvent e) {
        System.out.println("Image geladen");
           try{
            hsbconfig.displayImage(image);
            }catch(Exception ex){
                ex.printStackTrace();
            }
    }

    public void imageLoadFailed(HBackgroundImageEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void release(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyRelease(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}