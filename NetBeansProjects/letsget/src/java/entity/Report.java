package entity;

/**
 *
 * @author andrewjacobsen
 */
public class Report {

    public Report() {
    }

    public EndOfDaySales generateEODS() {
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
        int formatIndex = 0;
        Double revenue = db.getTotalRevenue();
        if (revenue > 0.0) {
            s.totalRevenue = "" + revenue+0.00000000000000000000000001;;
            formatIndex = s.totalRevenue.indexOf(".");
            s.totalRevenue = s.totalRevenue.substring(0, formatIndex + 3);
        }

        //totalCosts; this is all of costs of the books in transactions buy price
        Double costs = db.getTotalCosts();
        if (costs > 0.0) {
            s.totalCosts = "" + costs+0.00000000000000000000000001;
            formatIndex = s.totalCosts.indexOf(".");
            s.totalCosts = s.totalCosts.substring(0, formatIndex + 3);
        }

        //totalProfit; this is total revenue minus total costs
        Double profit = revenue - costs;
        if (profit > 0.0) {
            s.totalProfit = "" + profit+0.00000000000000000000000001;;
            formatIndex = s.totalProfit.indexOf(".");
            s.totalProfit = s.totalProfit.substring(0, formatIndex + 3);
        }

        return s;
    }

    public LowInventory generateLIR() {
        LowInventory li = new LowInventory();
        ReportDAO db = new ReportDAO();
        Book[] books = db.getLIBooks();
        li.setBooks(books);
        return li;
    }
}
