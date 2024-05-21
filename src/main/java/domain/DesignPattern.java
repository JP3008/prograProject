package domain;

import java.io.IOException;


public class DesignPattern {

        private int id;
        private String context;
        private String problem;
        private String solution;
        private String example;
        private String classification;
        private String image;

        public DesignPattern( String context, String problem, String solution, String example, String classification, String imageSource) throws IOException {
            this.id = util.Utility.getMaxID();
            this.context = context;
            this.problem = problem;
            this.solution = solution;
            this.example = example;
            this.classification = classification;
            this.image = util.Utility.encode(imageSource);
        }

        public DesignPattern(String context, String problem, String solution, String classification, String imageSource) throws IOException {
            this.id = util.Utility.getMaxID();
            this.context = context;
            this.problem = problem;
            this.solution = solution;
            this.example = null;
            this.classification = classification;
            this.image = util.Utility.encode(imageSource);
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getSolution() {
            return solution;
        }

        public void setSolution(String solution) {
            this.solution = solution;
        }

        public String getExample() {
            return example;
        }

        public void setExample(String example) {
            this.example = example;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String imagePath) throws IOException {
            this.image = util.Utility.encode(imagePath);
        }


}





