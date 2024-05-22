package cr.ac.ucr.progra2.paraiso.prograproject.domain;

import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;

import java.io.IOException;


public class DesignPattern {

        private int id;
        private String context;
        private String problem;
        private String solution;
        private String example;
        private String classification;
        private String image;

        public DesignPattern(){

        }

        public DesignPattern(String context, String problem, String solution, String example, String classification, String imageSource) throws IOException {
            this.id = Utility.getMaxID();
            this.context = context;
            this.problem = problem;
            this.solution = solution;
            this.example = example;
            this.classification = classification;
            this.image = Utility.encode(imageSource);
        }

        public DesignPattern(String context, String problem, String solution, String classification, String imageSource) throws IOException {
            this.id = Utility.getMaxID();
            this.context = context;
            this.problem = problem;
            this.solution = solution;
            this.example = null;
            this.classification = classification;
            this.image = Utility.encode(imageSource);
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
            this.image = Utility.encode(imagePath);
        }


}





