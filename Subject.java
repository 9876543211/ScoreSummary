package JavaTest.main;

    enum Subject {
        MATH("MATH"), 
        SCIENCE("SCIENCE"), 
        ENGLISH("ENGLISH"); 
        
        private String subjname; 
        private Subject(String subjname) { 
            this.subjname = subjname; 
        } 
        
        @Override 
        public String toString(){ 
            return subjname; 
        } 
    } 