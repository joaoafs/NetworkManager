package prr.core;

public abstract class TariffPlan {
    private String _name;

    public TariffPlan(String name){
        _name = name;
    }

    public String getTariffName(){
        return _name;
    }
    
}
