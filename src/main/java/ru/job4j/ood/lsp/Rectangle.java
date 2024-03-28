package ru.job4j.ood.lsp;

class Rectangle {
    protected double width;
    protected double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateArea() {
        return width * height;
    }


    class Square extends Rectangle {
        public Square(double sideLength) {
            super(sideLength, sideLength);
        }

        @Override
        public void setWidth(double width) {
            super.setWidth(width);
            super.setHeight(width);
        }

        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            super.setWidth(height);
        }
    }
}
