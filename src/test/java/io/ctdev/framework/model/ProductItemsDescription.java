package io.ctdev.framework.model;

import java.util.ArrayList;
import java.util.List;

public class ProductItemsDescription {
    private List<String> itemsDescription = new ArrayList<String>();

    public String getItemDescriptionByName(String productName) {
        switch (productName) {
            case "Apple Juice (1000ml)": {
                return "The all-time classic.";
            }
            case "Apple Pomace": {
                return "Finest pressings of apples. Allergy disclaimer: Might contain traces of worms. Can be sent back to us for recycling.";
            }
            case "Banana Juice (1000ml)": {
                return "Monkeys love it the most.";
            }
            case "Carrot Juice (1000ml)": {
                return "As the old German saying goes: \"Carrots are good for the eyes. Or has anyone ever seen a rabbit with glasses?\"";
            }
            case "Eggfruit Juice (500ml)": {
                return "Now with even more exotic flavour.";
            }
            case "Fruit Press": {
                return "Fruits go in. Juice comes out. Pomace you can send back to us for recycling purposes.";
            }
            case "Green Smoothie": {
                return "Looks poisonous but is actually very good for your health! Made from green cabbage, spinach, kiwi and grass.";
            }
            case "Juice Shop Adversary Trading Card (Common)": {
                return "Common rarity \"Juice Shop\" card for the Adversary Trading Cards CCG.";
            }
            case "Juice Shop Artwork": {
                return "Unique masterpiece painted with different kinds of juice on 90g/m² lined paper.";
            }
            case "Lemon Juice (500ml)": {
                return "Sour but full of vitamins.";
            }
            case "Melon Bike (Comeback-Product 2018 Edition)": {
                return "The wheels of this bicycle are made from real water melons. You might not want to ride it up/down the curb too hard.";
            }
            default:
                return "Sorry, but driver didn't find appropriate product description";
        }
    }

    public String getItemDescriptionByPositionNumber(int productPositionNumber) {
        return itemsDescription.get(productPositionNumber);
    }



    {
        itemsDescription.add(0, "null");
        itemsDescription.add(1, "The all-time classic.");
        itemsDescription.add(2, "Finest pressings of apples. Allergy disclaimer: Might contain traces of worms. Can be sent back to us for recycling.");
        itemsDescription.add(3, "Monkeys love it the most.");
        itemsDescription.add(4, "As the old German saying goes: \"Carrots are good for the eyes. Or has anyone ever seen a rabbit with glasses?\"");
        itemsDescription.add(5, "Now with even more exotic flavour.");
        itemsDescription.add(6, "Fruits go in. Juice comes out. Pomace you can send back to us for recycling purposes.");
        itemsDescription.add(7, "Looks poisonous but is actually very good for your health! Made from green cabbage, spinach, kiwi and grass.");
        itemsDescription.add(8, "Common rarity \"Juice Shop\" card for the Adversary Trading Cards CCG.");
        itemsDescription.add(9, "Super rare \"Juice Shop\" card with holographic foil-coating for the Adversary Trading Cards CCG.");
        itemsDescription.add(10, "Unique masterpiece painted with different kinds of juice on 90g/m² lined paper.");
        itemsDescription.add(11, "Sour but full of vitamins.");
        itemsDescription.add(12, "The wheels of this bicycle are made from real water melons. You might not want to ride it up/down the curb too hard.");
    }


}
