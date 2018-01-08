# 葫芦娃大作业设计说明

### 说明
完成了葫芦娃与妖怪的对战功能，支持暂停、重置、回放。  
完成了重要方法的单元测试。  
基于面向对象原则，每个生物的战斗由自己管理，包括搜索敌人、向敌人靠近、以及攻击，模拟了现实中由本身控制的观察、前进、攻击能力。 当生物被攻击时，也由自己的死亡概率决定是否死亡。 
所需的其他生物的位置信息由Field对象传递，计算出下一步要走的位置后向Field对象传递想要移动的愿望，这里运用了同步机制防止两个生物走到同一位置上。
使用了计时器来记录和读取保存的战斗场景。  
复用了前几次葫芦娃作业的代码。


### 面向对象概念
* 封装了Creature、Formation、Position三个基本类。
* 使用基类Creature和Formation分别代表生物和阵型。
* 使用了接口CommonValue来设置一些常量。
* 在存储Position对象时使用了范型队列。
* 使用了静态函数来进行一些通用计算。

### 面向对象机制
* 使用了继承机制：  
基类Creature有衍生类Huluwa、Grandpa、Scorpion、Snake、Louluo。  
基类Formation有衍生类Changshe、HeYi、YanXing。
* 使用了组合机制：  
Formation类中组合了Position[]用来记录生物的位置。
* 使用了聚集机制：  
Formation类中聚集了Creature[]，引用了应该排列的生物。
Field类中使用队列聚集了Position对象，在有生物的位置引用了Formation中的Position值。

### 设计理念
* 整体的设计思路是将现实中的生物、位置、阵型、场地对象映射到程序中形成类Creature、Position、Formation、Field。  
* 遵循单一职责原则。
 1. Creature类负责决策打斗（包括搜索敌人、决定向哪个位置进发、决定攻击哪个敌人、自己被攻击时是否死亡）。
 2. Position类负责承载生物以及记录自己的位置。
 3. Formation类负责安排生物在阵型中的相对位置。
 4. Field类负责管理战斗场面（包括布置阵型、记录生物位置、协调各个生物的移动，以及显示战斗场面和战斗状态）。
 5. Ground类负责计时来记录战斗场景以及回放记录。
 6. FileOperation类负责处理读写的文件。
* 遵循里氏替换原则。Creature可以被Huluwa、Grandpa、Scorpion、Snake、Louluo所替代，Formation可以被Changshe、HeYi、YanXing所替代。
* Creature基类和Formation基类将大部分的共同职责抽象出来，使得衍生类只需要具体实现自己的形象（image）和安排阵型（即arrange()方法）。

### 

### 设计优点
* 从分类学的角度将现实的各个对象抽象出来，使得类之间弱耦合，各自的职责划分清晰。
* 基本做到复用之前作业的代码。
