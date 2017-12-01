
package entity;

/**
 *
 * @author andrewjacobsen
 */
public class Report {
    
    public Report(){
    }
    
    public EndOfDaySales generateEODS(){
        EndOfDaySales s = new EndOfDaySales(true);
        ReportDAO db = new ReportDAO();
        ////totalOrders need to be found
        s.totalOrders = db.getTotalOrders();
        //totalBooksSold need to be found
        s.totalBooksSold = db.getTotalBooks();
        //visaSales need to be found
        //masterCardSales need to be found
        //americanExpressSales need to be found
        //discoverSales; need to be found
        //capitalOneSales; need to be found
        String[] cards = db.getCardTotals();
        s.visaSales = cards[0];
        s.masterCardSales = cards[1];
        s.americanExpressSales = cards[2];
        s.discoverSales = cards[3];
        s.capitalOneSales = cards[4];
        //totalRevenue; this is all of the money in transactions
        Double revenue = db.getTotalRevenue();
        s.totalRevenue = ""+revenue;
        int formatIndex = s.totalRevenue.indexOf(".");
        s.totalRevenue = s.totalRevenue.substring(0, formatIndex+3);
        //totalCosts; this is all of costs of the books in transactions buy price
        Double costs = db.getTotalCosts();
        s.totalCosts = ""+costs;
        formatIndex = s.totalCosts.indexOf(".");
        s.totalCosts=s.totalCosts.substring(0, formatIndex+3);
        //totalProfit; this is total revenue minus total costs
        Double profit = revenue-costs;
        s.totalProfit=""+profit;
        formatIndex = s.totalProfit.indexOf(".");
        s.totalProfit=s.totalProfit.substring(0, formatIndex+3);
        return s;
    }
    
}
