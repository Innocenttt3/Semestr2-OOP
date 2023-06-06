public class TransformationDecorator {
    private boolean translate;
    private Vec2 translateVector;
    private boolean rotate;
    private double rotateAngle;
    private Vec2 rotateCenter;
    private boolean scale;
    private Vec2 scaleVector;

    public Builder makeTranslate(Vec2 exampleVector) {
        this.translate = true;
        this.translateVector = exampleVector;
        return
    }

    public class Builder {
        private boolean translate = false;
        private Vec2 translateVector;

        private boolean rotate = false;
        private double rotateAngle;
        private Vec2 rotateCenter;

        private boolean scale = false;
        private Vec2 scaleVector;
    }

}
