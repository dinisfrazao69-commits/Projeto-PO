package erp.core;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Company implements Serializable{
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;
    
    
    private String _name;
    private int _id;
    private Map<Integer, SuppliedProduct> _produtos;

    public Company(int id, String name){
        this._id = id;
        this._name = name;
        this._produtos = new TreeMap<>();
    }

    public String getName(){
        return this._name;
    }

    public int getId(){
        return this._id;
    }

    public SuppliedProduct getProduct(int id){
        return this._produtos.get(id);
    }

    public void addProduct(SuppliedProduct produto){
        this._produtos.put(produto.getProduct().getId(), produto);
    }

    public List<SuppliedProduct> listProducts(){
        List<SuppliedProduct> lista = new ArrayList<>();
        for (SuppliedProduct p: this._produtos.values()){
            lista.add(p);
        }
        return lista;
    }

    @Override 
    public String toString(){
        return this._id + " - " + this._name;
    }
}
