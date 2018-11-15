package Model;

public enum Option
{
    Apartment,Premiumsuite;
    private Option()
    {}
    public String value()
    {
        return name();
    }
    public static Option fromvalue(String v)
    {
        return valueOf(v);
    }
}
