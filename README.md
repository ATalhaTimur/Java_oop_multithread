# EcoSim "Java_oop_multithread"

### Preface
In this project, the power of object-oriented programming principles has been fully utilized. Fundamental concepts such as abstract classes, enum structures, and inheritance form the foundation of the project. Thanks to these structures, the reusability, readability, and maintainability of the code have been significantly enhanced. The project also places great emphasis on performance optimization, applying multiprocess techniques in functions that require intensive computation. As a result, the speed and efficiency of the simulation have been substantially improved. For more details and technical information, please refer to the relevant sections of the document.

### Project Objective
This project is a Java console application that simulates the interactions between different types of animals and a hunter. The simulation encompasses behaviors such as movement, hunting, and reproduction of the animals. At the end of the simulation, the current numbers of the animals are displayed.

### Main Classes and Packages
**MainClasses**: Contains the basic classes.
- **Animal**: The base class for all animals, each with a unique ID, range of movement, gender, and location.
- **Prey**: The base class for prey animals, derived from the Animal class.
- **Predator**: The base class for predator animals, derived from the Animal class and equipped with hunting capabilities.
- **Gender**: An enum indicating the gender of the animals (MALE, FEMALE).
- **Zoo**: The main class of the simulation, managing the initiation of animals and the hunter, as well as simulating their movements and interactions.
  
**Animals**: Hosts classes for different animal species such as Chicken, Cow, Sheep, Rooster, Wolf, Lion, which define the behaviors of the respective animal species. They are derived from either Prey or Predator classes.

**Hunter**: Represents the hunter class, derived from the Predator class with unique hunting capabilities.

### Workflow of the Simulation
The main method of the Zoo class initiates the simulation. The `initializeAnimals` method creates a specified number and type of animals and stores them in an ArrayList. The `simulateMovements` method allows all animals and the hunter to move in random directions and within specified movement ranges. The `checkPredation` method enables predator animals and the hunter to hunt prey animals within their range. The `checkReproduction` method allows animals to reproduce under suitable conditions, creating new offspring. These steps are repeated over 1000 movement cycles. At the end of the simulation, the `printAnimalCounts` method displays the current count of each animal species on the console.

### How to Run
On a system with Java Development Kit (JDK) installed, import the source codes into an IDE (IntelliJ IDEA, Eclipse, etc.). Locate the MainClasses package containing the Zoo class. Start the simulation by running the main method of the Zoo class. This documentation provides a summary of the project's basic structure and functioning. The details of the simulation and specific behaviors can be better understood by examining the source codes.

### Performance Improvements
Performance enhancements in the Zoo Simulation Project are of great importance in terms of the simulation's speed and efficiency because the interaction of numerous animals creates a complex ecosystem dynamic. A series of improvements have been applied to animate this dynamic environment smoothly and effectively.

To accelerate reproduction processes, `parallelStream` is used in the `checkReproduction` method. This modern Java feature allows operations to be executed in parallel across multiple processor cores, significantly speeding up the processes.

The hunting strategies of predator animals have also been revised, with significant improvements made in the `checkPredation` method. Predator animals now focus only on prey within their hunting range, using their energy more effectively. This approach makes the operations within the simulation more efficient.

The movement mechanisms of the animals have been enhanced as well. The `move` method in the `Animal` class includes additional controls to ensure animals do not go beyond set boundaries. These controls cause animals to turn back when reaching the limits of the area and continue moving in a valid direction, leading to a more orderly and error-free progression of the simulation.

Assigning a unique ID to each animal also facilitates management. This makes it much easier to track the movements, interactions, and changes of animals within the simulation, especially for newborn animals and during hunting events.

These improvements ensure that the simulation operates smoothly, even in large-scale scenarios. As the number of animals increases and interactions intensify, the effects of these enhancements become even more pronounced, making the simulation both faster and more efficient.

### UML class diagram

![proje2](https://github.com/ATalhaTimur/Java_oop_multithread/assets/93510585/f83ab1ec-031a-4950-afb8-c9af5c95228b)

![ProjectUml](https://github.com/ATalhaTimur/Java_oop_multithread/assets/93510585/085ef005-1309-40d2-b0a1-ad079c4bb2fb)

Project Screenshots
The project's runtime decreases significantly with JVM optimization.

![Ekran görüntüsü 2024-03-14 041216](https://github.com/ATalhaTimur/Java_oop_multithread/assets/93510585/d6ac44f4-d0f2-42f2-83c3-e9f747df02e9)

![Ekran görüntüsü 2024-03-14 040927](https://github.com/ATalhaTimur/Java_oop_multithread/assets/93510585/a5560f6b-5361-4283-a1a6-e15330ec4ae1)

![Ekran görüntüsü 2024-03-14 043413](https://github.com/ATalhaTimur/Java_oop_multithread/assets/93510585/717e0af7-f890-4f6f-929d-76a42b1cd721)

### Situations Where the Project Does Not Work (if the project does not stop when executed)

**Solution:** 
Restart the project until results are produced; once results are obtained for the first time, subsequent requests come directly as the JVM compiler becomes quicker to prepare.
**Reason for this issue (based on my research):**

The Java Virtual Machine (JVM) and its "Just-In-Time" (JIT) compiler.
The JVM performs a series of optimizations during the execution of Java code. On the first run, the code is considered "cold"; meaning, the JIT compiler has not yet optimized the code.
This can cause the code to run slower. As the program runs and certain pieces of code are used repeatedly, the JIT compiler identifies these codes as "hot" and compiles or optimizes them to improve runtime performance.
The long duration you observed in your first run could be related to the JVM interpreting the code and the JIT compiler beginning to optimize. When you stop and restart the program, the JVM might have already made some optimizations and can execute especially frequently run portions of the code more quickly. This results in a significant performance increase on the second and subsequent runs. Additionally, there are startup costs during the initial loading of Java applications, such as class loaders loading classes, initialization of static blocks, and allocation of necessary resources.
When the program is run again, the JVM and operating system might have cached some resources and data, making subsequent runs faster.

