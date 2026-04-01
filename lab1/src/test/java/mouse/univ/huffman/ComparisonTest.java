package mouse.univ.huffman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparisonTest {

    @Test
    void compareSherlockHolmes() {
        TextFrequencyGenerator generator = new TextFrequencyGenerator();
        String paragraph = """
                In the year 1878 I took my degree of
                Doctor of Medicine of the University of
                London, and proceeded to Netley to go
                through the course prescribed for surgeons in the army. Having completed my studies
                there, I was duly attached to the Fifth Northumberland Fusiliers as Assistant Surgeon. The regiment
                was stationed in India at the time, and before I
                could join it, the second Afghan war had broken
                out. On landing at Bombay, I learned that my corps
                had advanced through the passes, and was already
                deep in the enemy’s country. I followed, however,
                with many other officers who were in the same
                situation as myself, and succeeded in reaching Candahar in safety, where I found my regiment, and at
                once entered upon my new duties.
                The campaign brought honours and promotion
                to many, but for me it had nothing but misfortune
                and disaster. I was removed from my brigade and
                attached to the Berkshires, with whom I served at
                the fatal battle of Maiwand. There I was struck on
                the shoulder by a Jezail bullet, which shattered the
                bone and grazed the subclavian artery. I should
                have fallen into the hands of the murderous Ghazis
                had it not been for the devotion and courage shown
                by Murray, my orderly, who threw me across a
                pack-horse, and succeeded in bringing me safely to
                the British lines.
                Worn with pain, and weak from the prolonged
                hardships which I had undergone, I was removed,
                with a great train of wounded sufferers, to the base
                hospital at Peshawar. Here I rallied, and had already improved so far as to be able to walk about
                the wards, and even to bask a little upon the verandah, when I was struck down by enteric fever, that
                curse of our Indian possessions. For months my life
                was despaired of, and when at last I came to myself
                and became convalescent, I was so weak and emaciated that a medical board determined that not a day
                should be lost in sending me back to England. I was
                dispatched, accordingly, in the troopship Orontes,
                and landed a month later on Portsmouth jetty, with
                my health irretrievably ruined, but with permission
                from a paternal government to spend the next nine
                months in attempting to improve it.
                I had neither kith nor kin in England, and was
                therefore as free as air—or as free as an income
                of eleven shillings and sixpence a day will permit
                a man to be. Under such circumstances, I naturally gravitated to London, that great cesspool into
                which all the loungers and idlers of the Empire are
                irresistibly drained. There I stayed for some time at
                a private hotel in the Strand, leading a comfortless,
                meaningless existence, and spending such money
                as I had, considerably more freely than I ought. So
                alarming did the state of my finances become, that
                I soon realized that I must either leave the metropolis and rusticate somewhere in the country, or that
                I must make a complete alteration in my style of
                living. Choosing the latter alternative, I began by
                making up my mind to leave the hotel, and to take
                up my quarters in some less pretentious and less
                expensive domicile.
                On the very day that I had come to this conclusion, I was standing at the Criterion Bar, when
                some one tapped me on the shoulder, and turning
                round I recognized young Stamford, who had been
                a dresser under me at Bart’s. The sight of a friendly
                face in the great wilderness of London is a pleasant
                thing indeed to a lonely man. In old days Stamford
                had never been a particular crony of mine, but now
                I hailed him with enthusiasm, and he, in his turn,
                appeared to be delighted to see me. In the exuberance of my joy, I asked him to lunch with me at the
                Holborn, and we started off together in a hansom.
                """;


        CharacterProbabilitySet charFreq = generator.generate(paragraph, false);
        charFreq.print(System.out);

        RegularEncoder regularEncoder = new RegularEncoder();
        CharacterEncoding regularEncoding = regularEncoder.encode(charFreq);
        int bitsRegular = regularEncoding.write(paragraph).length();
        System.out.println("Bits regular:" + bitsRegular);

        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        CharacterEncoding huffmanEncoding = huffmanEncoder.encode(charFreq);
        int bitsHuffman = huffmanEncoding.write(paragraph).length();
        System.out.println("Bits Huffman:" + bitsHuffman);
        System.out.println("Compression:" + (double) bitsHuffman / bitsRegular);

        Assertions.assertTrue(bitsRegular > bitsHuffman);
    }

    @Test
    void compareCharlie() {
        TextFrequencyGenerator generator = new TextFrequencyGenerator();
        String paragraph = """
                These two very old people are the father and mother of Mr Bucket.
                                   Their names are Grandpa Joe and Grandma Josephine.
                                   And these two very old people are the father and mother of Mrs
                                   Bucket. Their names are Grandpa George and Grandma Georgina.
                                   This is Mr Bucket. This is Mrs Bucket.
                                   Mr and Mrs Bucket have a small boy whose name is Charlie Bucket.
                                   This is Charlie.
                                   How d’you do? And how d’you do? And how d’you do again? He is
                                   pleased to meet you.
                                   The whole of this family – the six grown-ups (count them) and little
                                   Charlie Bucket – live together in a small wooden house on the edge of a
                                   great town.
                                   The house wasn’t nearly large enough for so many people, and life
                                   was extremely uncomfortable for them all. There were only two rooms
                                   in the place altogether, and there was only one bed. The bed was given
                                   to the four old grandparents because they were so old and tired. They
                                   were so tired, they never got out of it.
                                   Grandpa Joe and Grandma Josephine on this side, Grandpa George
                                   and Grandma Georgina on this side.
                                   Mr and Mrs Bucket and little Charlie Bucket slept in the other room,
                                   upon mattresses on the floor.
                                   In the summertime, this wasn’t too bad, but in the winter, freezing
                                   cold draughts blew across the floor all night long, and it was awful.
                                   There wasn’t any question of them being able to buy a better house –
                                   or even one more bed to sleep in. They were far too poor for that.
                                   Mr Bucket was the only person in the family with a job. He worked in
                                   a toothpaste factory, where he sat all day long at a bench and screwed
                                   the little caps on to the tops of the tubes of toothpaste after the tubes
                                   had been filled. But a toothpaste cap-screwer is never paid very much
                                   money, and poor Mr Bucket, however hard he worked, and however fast
                                   he screwed on the caps, was never able to make enough to buy one half
                                   of the things that so large a family needed. There wasn’t even enough
                                   money to buy proper food for them all. The only meals they could afford
                                   were bread and margarine for breakfast, boiled potatoes and cabbage for
                                   lunch, and cabbage soup for supper. Sundays were a bit better. They all
                                   looked forward to Sundays because then, although they had exactly the
                                   same, everyone was allowed a second helping.
                                   The Buckets, of course, didn’t starve, but every one of them – the two
                                   old grandfathers, the two old grandmothers, Charlie’s father, Charlie’s
                                   mother, and especially little Charlie himself – went about from morning
                                   till night with a horrible empty feeling in their tummies.
                                   Charlie felt it worst of all. And although his father and mother often
                                   went without their own share of lunch or supper so that they could give
                                   it to him, it still wasn’t nearly enough for a growing boy. He desperately
                                   wanted something more filling and satisfying than cabbage and cabbage
                                   soup. The one thing he longed for more than anything else was…
                                   CHOCOLATE.
                                   Walking to school in the mornings, Charlie could see great slabs of
                                   chocolate piled up high in the shop windows, and he would stop and
                                   stare and press his nose against the glass, his mouth watering like mad.
                                   Many times a day, he would see other children taking bars of creamy
                                   chocolate out of their pockets and munching them greedily, and that, of
                                   course, was pure torture.
                                   Only once a year, on his birthday, did Charlie Bucket ever get to taste
                                   a bit of chocolate. The whole family saved up their money for that
                                   special occasion, and when the great day arrived, Charlie was always
                                   presented with one small chocolate bar to eat all by himself. And each
                                   time he received it, on those marvellous birthday mornings, he would
                                   place it carefully in a small wooden box that he owned, and treasure it
                                   as though it were a bar of solid gold; and for the next few days, he
                                   would allow himself only to look at it, but never to touch it. Then at last,
                                   when he could stand it no longer, he would peel back a tiny bit of the
                                   paper wrapping at one corner to expose a tiny bit of chocolate, and then
                                   he would take a tiny nibble – just enough to allow the lovely sweet taste
                                   to spread out slowly over his tongue. The next day, he would take
                                   another tiny nibble, and so on, and so on. And in this way, Charlie
                                   would make his sixpenny bar of birthday chocolate last him for more
                                   than a month.
                                   But I haven’t yet told you about the one awful thing that tortured
                                   little Charlie, the lover of chocolate, more than anything else. This thing,
                                   for him, was far, far worse than seeing slabs of chocolate in the shop
                                   windows or watching other children munching bars of creamy chocolate
                                   right in front of him. It was the most terrible torturing thing you could
                                   imagine, and it was this:
                                   In the town itself, actually within sight of the house in which Charlie
                                   lived, there was an ENORMOUS CHOCOLATE FACTORY!
                                   Just imagine that!
                                   And it wasn’t simply an ordinary enormous chocolate factory, either.
                                   It was the largest and most famous in the whole world! It was WONKA’S
                                   FACTORY, owned by a man called Mr Willy Wonka, the greatest
                                   inventor and maker of chocolates that there has ever been. And what a
                                   tremendous, marvellous place it was! It had huge iron gates leading into
                                   it, and a high wall surrounding it, and smoke belching from its
                                   chimneys, and strange whizzing sounds coming from deep inside it. And
                                   outside the walls, for half a mile around in every direction, the air was
                                   scented with the heavy rich smell of melting chocolate!
                                   Twice a day, on his way to and from school, little Charlie Bucket had
                                   to walk right past the gates of the factory. And every time he went by,
                                   he would begin to walk very, very slowly, and he would hold his nose
                                   high in the air and take long deep sniffs of the gorgeous chocolatey smell
                                   all around him.
                                   Oh, how he loved that smell!
                                   And oh, how he wished he could go inside the factory and see what it
                                   was like!
                """;


        CharacterProbabilitySet charFreq = generator.generate(paragraph, false);
        charFreq.print(System.out);

        RegularEncoder regularEncoder = new RegularEncoder();
        CharacterEncoding regularEncoding = regularEncoder.encode(charFreq);
        int bitsRegular = regularEncoding.write(paragraph).length();
        System.out.println("Bits regular:" + bitsRegular);

        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        CharacterEncoding huffmanEncoding = huffmanEncoder.encode(charFreq);
        int bitsHuffman = huffmanEncoding.write(paragraph).length();
        System.out.println("Bits Huffman:" + bitsHuffman);
        System.out.println("Compression:" + (double) bitsHuffman / bitsRegular);

        Assertions.assertTrue(bitsRegular > bitsHuffman);
    }
}
