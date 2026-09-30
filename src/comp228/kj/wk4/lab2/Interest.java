package comp228.kj.wk4.lab2;

// Import classes for big decimals and rounding
import java.math.BigDecimal;
import java.math.RoundingMode;

// Create Interest class
public class Interest {
    // Instance variables (fields)
    private BigDecimal principal;
    private BigDecimal rate;
    private int time;
    // time could be either an integer or double;

    // Simple interest formula: SI = P*R*T / 100;

    // Constructor (sets values and validates inputs)
    public Interest(BigDecimal principal, BigDecimal rate, int time) {

        // Check if principal is null/less than/equal to zero
        if (principal == null || principal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Principal must be greater than zero.");
        }

        // Check if rate is null/negative
        if (rate == null || rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Rate cannot be negative.");
        }

        // Check if time is zero/negative
        if (time <= 0) {
            throw new IllegalArgumentException("Time must be greater than zero.");
        }

        // Assign values to instance variables
        this.principal = principal;
        this.rate = rate;
        this.time = time;
    }

    // Method 1 ( for simple interest)
    public BigDecimal calculateSimpleInterest(BigDecimal p, BigDecimal r, int t) {
        // Compound interest formula: P * R * T / 100
        return p.multiply(r)
                .multiply(BigDecimal.valueOf(t))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    // Simple Interest (using class instance variables)
    public BigDecimal calculateSimpleInterest() {
        return calculateSimpleInterest(this.principal, this.rate, this.time);
    }

    // Simple Interest (using Double, method overloading)
    public double calculateSimpleInterest(double p, double r, int t) {
        // Simple formula using double math
        double result = (p * r * t) / 100.0;
        return result;
    }

    // Method 2 (for compound interest)
    public BigDecimal calculateCompoundInterest(BigDecimal p, BigDecimal r, int t) {
        // Convert rate percentage to decimal (r / 100)
        BigDecimal rateFrac = r.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        // Calculate amount: P * (1 + rateFrac)^T
        BigDecimal amount = p.multiply(BigDecimal.ONE.add(rateFrac).pow(t));
        // Subtract principal to get interest only
        return amount.subtract(p).setScale(2, RoundingMode.HALF_UP);
    }

    // Compound Interest (using class instance variables)
    public BigDecimal calculateCompoundInterest() {
        return calculateCompoundInterest(this.principal, this.rate, this.time);
    }

    // Compound Interest (using Double, method overloading)
    public double calculateCompoundInterest(double p, double r, int t) {
        double amount = p * Math.pow(1 + (r / 100.0), t);
        return amount - p;
    }

    // Getter method for principal
    public BigDecimal getPrincipal() { 
        return principal; 
    }

    // Getter method for rate
    public BigDecimal getRate() { 
        return rate; 
    }

    // Getter method for time
    public int getTime() { 
        return time; 
    }
}
