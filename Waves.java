public class Waves {

    static Monster[][] waves = {
        {new Zombie()}, //wave1

        {new Zombie(), new Zombie()}, //wave2

        {new Zombie(), new Zombie(), new Zombie(), new Zombie(),}, //wave3

        {new Zombie(), new Zombie(), new Rogue()}, //wave4

        {new Rogue(), new Rogue()}, //wave5

        {new Zombie(), new Rogue(), new Rogue(), new Zombie()}, //wave6

        {new Rogue(), new Rogue(), new Zombie(), new Rogue()}, //wave7

        {new Rogue(), new Rogue(), new Rogue()}, //wave8

        {new Zombie(), new Zombie(), new Zombie(), new Zombie(), new Zombie(), new Zombie()}, //wave9

        {new Ork()}, //wave10

        {new Zombie(), new Ork()}, //wave11

        {new Zombie(),new Rogue(), new Ork()}, //wave12

        {new Zombie(), new Rogue(), new Zombie(), new Ork()}, //wave13

        {new Rogue(), new Rogue(), new Rogue(), new Rogue()}, //wave14

        {new Ork(), new Zombie()}, //wave15

        {new Rogue(), new Rogue(), new Rogue(), new Ork()}, //wave16

        {new Rogue(), new Rogue(), new Ork()}, //wave17

        {new Zombie(), new Zombie(), new Zombie(), new Zombie()}, //wave18

        //{new Rogue(), new Ork(), new Ork()}, //wave19
        {new Zombie()},

        {new BoguJerma()} //wave20
    };

}
