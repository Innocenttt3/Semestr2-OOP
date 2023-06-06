import java.util.Locale;

public class TransformationDecorator extends ShapeDecorator{
    private boolean translate;
    private Vec2 translateVector;
    private boolean rotate;
    private double rotateAngle;
    private Vec2 rotateCenter;

    public void setTranslateVector(Vec2 translateVector) {
        this.translateVector = translateVector;
    }

    public void setRotateAngle(double rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public void setRotateCenter(Vec2 rotateCenter) {
        this.rotateCenter = rotateCenter;
    }

    public void setScaleVector(Vec2 scaleVector) {
        this.scaleVector = scaleVector;
    }

    private boolean scale;
    private Vec2 scaleVector;

    public TransformationDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }
    public String toSvg(String parameters) {
        String output = "";
        if(translate == true){
            String translation = String.format("translate(%f %f) ", translateVector.x, translateVector.y);
            output += translation;
        }
        if(rotate == true){
            String rotation = String.format("rotate(%f %f %f) ", rotateAngle, rotateCenter.x, rotateCenter.y);
            output += rotation;
        }
        if(scale == true){
            String scaling = String.format("scale(%f %f) ", scaleVector.x, scaleVector.y);
            output += scaling;
        }
        output = String.format("\"transform=\\\"%s\\\" %s\"", output, parameters);
        return super.toSvg(output);
    }
}


    class Builder {
        private boolean translate = false;
        private Vec2 translateVector;
        private boolean rotate = false;
        private double rotateAngle;
        private Vec2 rotateCenter;
        private boolean scale = false;
        private Vec2 scaleVector;
        public Builder makeTranslate(Vec2 exampleVector) {
            this.translate = true;
            this.translateVector = exampleVector;
            Builder output = new Builder();
            return this;
        }
        public Builder makeRotate(double exampleRotateAngle) {
            this.rotate = true;
            this.rotateAngle = exampleRotateAngle;
            return this;
        }
        public Builder makeScale(Vec2 exampleScaleVector) {
            this.scale = true;
            this.scaleVector = exampleScaleVector;
            return this;
        }
        public TransformationDecorator createTransformationDecorator(Shape shape) {
            TransformationDecorator output = new TransformationDecorator(shape);
            output.setRotateAngle(this.rotateAngle);
            output.setRotateCenter(this.rotateCenter);
            output.setTranslateVector(this.translateVector);
            output.setScaleVector(this.scaleVector);
            return output;
    }

}
