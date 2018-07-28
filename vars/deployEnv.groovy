// vars/deployEnv.groovy
def call(String deployOnEnv) {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    // echo "Hello, ${name}."

     switch(deployOnEnv) {            
         //There is case statement defined for 4 cases 
         // Each case statement section has a break condition to exit the loop 
			
         case 'Development': 
            println("Selected Environment: Development"); 
            break; 
         case 'Stage': 
            println("Selected Environment: Stage"); 
            break; 
         case 'UAT': 
            println("Selected Environment: UAT"); 
            break; 
         case 'PreProd': 
            println("Selected Environment: PreProd"); 
            break; 
         case 'Production': 
            println("Selected Environment: Production"); 
            break; 
         default: 
            println("Unknown Environment...Skipping execuiton."); 
            exit 1;
            break; 
      }

}
