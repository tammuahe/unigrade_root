package com.tlu.unigrade.enums;

public enum Department {

    // Công nghệ & Kỹ thuật
    COMPUTER_SCIENCE("Khoa học máy tính"),
    INFORMATION_TECHNOLOGY("Công nghệ thông tin"),
    ARTIFICIAL_INTELLIGENCE("Trí tuệ nhân tạo"),
    COMPUTER_NETWORKS_AND_DATA_COMM("Mạng máy tính và truyền thông dữ liệu"),

    // Kinh tế & Quản trị
    BUSINESS_ADMINISTRATION("Quản trị kinh doanh"),
    E_COMMERCE("Thương mại điện tử"),
    MARKETING("Marketing"),
    INTERNATIONAL_ECONOMICS("Kinh tế quốc tế"),
    ACCOUNTING("Kế toán"),
    FINANCE_BANKING("Tài chính – Ngân hàng"),
    LOGISTICS_AND_SUPPLY_CHAIN("Logistics và Quản lý chuỗi cung ứng"),
    ECONOMIC_LAW("Luật kinh tế"),

    // Du lịch & Dịch vụ
    HOTEL_MANAGEMENT("Quản trị khách sạn"),
    TOURISM_AND_TRAVEL_MANAGEMENT("Quản trị dịch vụ du lịch – lữ hành"),

    // Truyền thông & Thiết kế
    GRAPHIC_DESIGN("Thiết kế đồ họa"),
    MULTIMEDIA_COMMUNICATION("Truyền thông đa phương tiện"),

    // Ngôn ngữ & Văn hóa
    ENGLISH_LANGUAGE("Ngôn ngữ Anh"),
    CHINESE_LANGUAGE("Ngôn ngữ Trung Quốc"),
    JAPANESE_LANGUAGE("Ngôn ngữ Nhật"),
    KOREAN_LANGUAGE("Ngôn ngữ Hàn Quốc"),
    VIETNAMESE_STUDIES("Việt Nam học"),

    // Y tế
    NURSING("Điều dưỡng"),

    // Nghệ thuật
    VOCAL_MUSIC("Thanh nhạc");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
