package me.arythite.arycrate.menu;

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;

    // 28 Empty Slots Per Page
    protected int maxItemsPerPage = 28;

    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

}
