package erp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Product implements Serializable{
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    private String _name;
    private int _id;
    private ProductCategory _category;
    private int _basePrice;
    private int _baseDeliveryTime;
    private List<SuppliedProduct> _supplies;
    
    public Product(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime){
        this._id = id;
        this._name = name;
        this._category = category;
        this._baseDeliveryTime = baseDeliveryTime;
        this._basePrice = basePrice;
        this._supplies = new ArrayList<>();
    }


    public int getId(){
        return this._id;
    }

    public String getName(){
        return this._name;
    }

    public ProductCategory getCategory(){
        return this._category;
    }

    public int getBasePrice(){
        return this._basePrice;
    }

    public int getBaseDeliveryTime(){
        return this._baseDeliveryTime;
    }

    public int totalUnits(){
        int total = 0; 
        for ( SuppliedProduct p: this._supplies){
            total += p.getQuantity();
        }
        return total;
    }

    void addSupply(SuppliedProduct p){
        this._supplies.add(p);
    }





    public abstract String type();

    public abstract double recommendedPrice();


    @Override
    public String toString(){
        return this._id + " - " + type() + " - " + this._name + " - " + this._category +
        " - " + Format.money(this._basePrice) + " - " + Format.money(this.recommendedPrice())
        + " - " + this._baseDeliveryTime + " - " + totalUnits();
    }
}