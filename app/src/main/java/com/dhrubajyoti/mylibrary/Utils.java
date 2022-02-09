package com.dhrubajyoti.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static Utils instance;

    private SharedPreferences sharedPreferences;
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WISHLIST_BOOKS = "wishlist_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVOURITE_BOOKS = "favourite_books";

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> wishlistBooks;
//    private static ArrayList<Book> favouriteBooks;

    public Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if(getAllBooks() == null){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if(getAlreadyReadBooks() == null){
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getCurrentlyReadingBooks() == null){
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getWishlistBooks() == null){
            editor.putString(WISHLIST_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getFavouriteBooks() == null){
            editor.putString(FAVOURITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }


    private void initData() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1,"The Happiest Man on Earth", "Eddie Jaku", 208,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781529066364.jpg",
                "The Happiest Man on Earth is a powerful, heartbreaking and ultimately hopeful memoir of how happiness can be found even in the darkest of times.",
                "Life can be beautiful if you make it beautiful. It is up to you.\n" +"\n" +
                "Eddie Jaku always considered himself a German first, a Jew second. He was proud of his country. But all of that changed in November 1938," +
                        " when he was beaten, arrested and taken to a concentration camp.\n" +"\n" +
                "Over the next seven years, Eddie faced unimaginable horrors every day, first in Buchenwald, then in Auschwitz," +
                        " then on a Nazi death march. He lost family, friends, his country."));
        books.add(new Book(2, "The Nightingale", "Kristin Hannah",464,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781509848621.jpg",
                "A rich, compelling novel of love, sacrifice and survival",
                "This story is about what it was like to be a woman during World War II when women’s stories were all too often forgotten " +
                        "or overlooked . . . Vianne and Isabelle Mauriac are two sisters, separated by years and experience, by ideals and passion and" +
                        " circumstance, each embarking on her own dangerous path towards survival, love and freedom in war-torn France.\n" +"\n" +
                        "Kristin Hannah's The Nightingale is a novel for everyone, a novel for a lifetime."));

        books.add(new Book(3,"Luster", "Raven Leilani", 240,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781529036008.jpg",
                "A book of pure fineness, exceptional.",
                "Edie is just trying to survive. She’s messing up in her dead-end admin job in her all-white office, is sleeping with all the wrong men," +
                        " and has failed at the only thing that meant anything to her, painting. No one seems to care that she doesn’t really know what she’s " +
                        "doing with her life beyond looking for her next hook-up. And then she meets Eric, a white middle-aged archivist with a suburban family," +
                        " including a wife who has sort-of-agreed to an open marriage and an adopted black daughter who doesn’t have a single person in her life" +
                        " who can show her how to do her hair. As if navigating the constantly shifting landscape of sexual and racial politics as a young black" +
                        " woman wasn’t already hard enough, with nowhere else left to go, Edie finds herself falling head-first into Eric’s home and family." +"\n\n" +
                "Razor-sharp, provocatively page-turning and surprisingly tender," +
                        " Luster by Raven Leilani is a painfully funny debut about what it means to be young now."));
        books.add(new Book(4, "Shuggie Bain", "Douglas Stuart", 448,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781529019292.jpg",
                "An amazingly intimate, compassionate, gripping portrait of addiction, courage and love",
                "It is 1981. Glasgow is dying and good families must grift to survive. Agnes Bain has always expected more from life," +
                        " dreaming of greater things. But Agnes is abandoned by her philandering husband, and as she descends deeper into drink," +
                        " the children try their best to save her, yet one by one they must abandon her to save themselves." +
                        " It is her son Shuggie who holds out hope the longest. Shuggie is different, he is clearly no’ right. " +
                        "But Shuggie believes that if he tries his hardest, he can be normal like the other boys and help his mother escape this hopeless place."));
        books.add(new Book(5,"Room", "Emma Donoghue", 432,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447276364.jpg",
                "A major film starring Brie Larson." +"Shortlisted for the Man Booker Prize." +"Shortlisted for the Orange Prize.",
                "Today I'm five. I was four last night going to sleep in Wardrobe, but when I wake up in Bed in the dark I'm changed to five," +
                        " abracadabra.\n\n" +
                        "Jack lives with his Ma in Room. Room has a single locked door and a skylight, and it measures ten feet by ten feet." +
                        " Jack loves watching TV but he knows that nothing he sees on the screen is truly real – only him, Ma and the things in Room." +
                        " Until the day Ma admits there is a world outside.\n\n" +
                        "Devastating yet uplifting, Room by Emma Donoghue is a luminous portrait of a boundless maternal love." +
                        " It has sold more than two million copies, was a number one bestseller and was shortlisted for the Man Booker and Orange prizes." +
                        " Few books have reached modern classic status so swiftly."));
        books.add(new Book(6, "Burial Rites","Hannah Kent",384,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447233176.jpg",
                "Agnes Magnúsdóttir is condemned to death for her part in the brutal murder of her lover.",
                "Agnes is sent to wait out her final months on the farm of district officer Jón Jónsson," +
                        " his wife and their two daughters. Horrified to have a convicted murderer in their midst, the family avoid contact with Agnes." +
                        " Only Tóti, the young assistant priest appointed Agnes’s spiritual guardian, is compelled to try to understand her." +
                        " As the year progresses and the hardships of rural life force the household to work side by side, Agnes’s story begins to emerge and " +
                        "with it the family’s terrible realization that all is not as they had assumed.\n\n" +
                        "Based on actual events, Burial Rites is an astonishing and moving novel about the truths we claim to know and the ways in which we" +
                        " interpret what we’re told. In beautiful, cut-glass prose, Hannah Kent portrays Iceland’s formidable landscape, in which every day is" +
                        " a battle for survival, and asks, how can one woman hope to endure when her life depends upon the stories told by others?"));
        books.add(new Book(7,"American Psycho","Bret Easton Ellis", 416,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447277705.jpg",
                "A cult classic, adapted into a film starring Christian Bale.","Is evil something you are? Or is it something you do?\n" +
                "\n" +
                "Patrick Bateman has it all: good looks, youth, charm, a job on Wall Street, reservations at every new restaurant in town and a line of" +
                " girls around the block. He is also a psychopath. A man addicted to his superficial, perfect life, he pulls us into a dark underworld where " +
                "the American Dream becomes a nightmare . . ."));
        books.add(new Book(8,"The Lovely Bones","The Lovely Bones",336,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447275206.jpg",
                "The internationally bestselling novel that inspired the acclaimed film directed by Peter Jackson.",
                "My name was Salmon, like the fish; first name, Susie. I was fourteen when I was murdered on December 6, 1973.\n" +"\n" +
                "In heaven, Susie Salmon can have whatever she wishes for – except what she most wants, which is to be back with the people she loved on earth." +
                        " In the wake of her murder, Susie watches as her happy suburban family is torn apart by grief; as her friends grow up, fall in love," +
                        " and do all the things she never had the chance to do herself. But as Susie will come to realize, even in death, life is not quite out" +
                        " of reach . . .\n" +"\n" +
                "A luminous, astonishing novel about life and death, memory and forgetting, and finding light in the darkest places, " +
                        "Alice Sebold's The Lovely Bones became an instant classic when it was first published. There are now over ten million copies in print." +
                        " It inspired the film starring Mark Wahlberg, Rachel Weisz, Susan Sarandon and Saoirse Ronan."));
        books.add(new Book(9,"A Little Life","Hanya Yanagihara",736,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447294832.jpg",
                "A Little Life by Hanya Yanagihara, is an immensely powerful and heartbreaking novel of brotherly love and the limits of human endurance.",
                "When four graduates from a small Massachusetts college move to New York to make their way, they're broke, adrift, " +
                        "and buoyed only by their friendship and ambition. There is kind, handsome Willem, an aspiring actor; JB, a quick-witted," +
                        " sometimes cruel Brooklyn-born painter seeking entry to the art world; Malcolm, a frustrated architect at a prominent firm;" +
                        " and withdrawn, brilliant, enigmatic Jude, who serves as their centre of gravity.\n" +"\n" +
                        "Over the decades, their relationships deepen and darken, tinged by addiction, success, and pride. Yet their greatest challenge," +
                        " each comes to realize, is Jude himself, by midlife a terrifyingly talented litigator yet an increasingly broken man, his mind " +
                        "and body scarred by an unspeakable childhood, and haunted by what he fears is a degree of trauma that he'll not only be unable to " +
                        "overcome – but that will define his life forever."));
        books.add(new Book(10,"Pride and Prejudice","Jane Austen",496,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781909621657.jpg",
                "One of BBC's 100 Novels That Shaped Our World.",
                "Jane Austen's best-loved novel is an unforgettable story about the inaccuracy of first impressions, the power of reason," +
                        " and above all the strange dynamics of human relationships and emotions.\n" +"\n" +
                        "Part of the Macmillan Collector’s Library; a series of stunning, clothbound, pocket sized classics with gold foiled edges and " +
                        "ribbon markers. These beautiful books make perfect gifts or a treat for any book lover. This edition is illustrated by Hugh Thomson " +
                        "and features an afterword by author and critic, Henry Hitchings.\n" +"\n" +
                        "A tour de force of wit and sparkling dialogue, Pride and Prejudice shows how the headstrong Elizabeth Bennet and the aristocratic" +
                        " Mr Darcy must have their pride humbled and their prejudices dissolved before they can acknowledge their love for each other."));
        books.add(new Book(11,"A House for Mr Biswas","V. S. Naipaul",640,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781509803507.jpg",
                "It has been hailed as one of the twentieth century's finest novels.",
                "He was struck again and again by the wonder of being in his own house, the audacity of it: to walk in through his own front gate, " +
                        "to bar entry to whoever he wished, to close his doors and windows every night.\n" +"\n" +
                        "Mr. Biswas has been told since the day of his birth that misfortune will follow him – and so it has." +
                        " Meaning only to avoid punishment, he causes the death of his father and the dissolution of his family." +
                        " Wanting simply to flirt with a beautiful woman, he ends up marrying her, and reluctantly relying on her domineering" +
                        " family for support. But in spite of endless setbacks, Mr. Biswas is determined to achieve independence, and so he begins his" +
                        " gruelling struggle to buy a home of his own."));
        books.add(new Book(12, "Passing","Nella Larsen",160,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781529040289.jpg",
                "A tragic story rooted in inescapable facts of American life . . .",
                "Part of the Macmillan Collector’s Library; a series of stunning, clothbound, pocket sized classics with gold foiled edges and" +
                        " ribbon markers. These beautiful books make perfect gifts or a treat for any book lover. This edition of Passing features an " +
                        "introduction by writer and academic, Christa Holm Vogelius.\n" +"\n" +
                        "Irene Redfield, married to a successful physician, enjoys a comfortable life in 1920s Harlem, New York. Reluctantly," +
                        " she renews her friendship with old school friend, Clare Kendry. Clare, who like Irene is light skinned, ‘passes’ as white" +
                        " and is married to a racist white man who has no idea about Clare’s racial heritage. Clare is very persuasive and Irene, despite" +
                        " misgivings, can’t resist letting her back into her world. As tensions mount between friends and between couples, this taut and" +
                        " mesmerizing narrative spins towards an unexpected end."));
        books.add(new Book(13,"Station Eleven","Emily St. John Mandel",384,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447268970.jpg",
                "Best novel. The big one . . . stands above all the others",
                "What was lost in the collapse: almost everything, almost everyone, but there is still such beauty.\n" +"\n" +
                        "One snowy night in Toronto famous actor Arthur Leander dies on stage whilst performing the role of a lifetime. That same evening a " +
                        "deadly virus touches down in North America. The world will never be the same again.\n" +"\n" +
                        "Twenty years later Kirsten, an actress in the Travelling Symphony, performs Shakespeare in the settlements that have grown up" +
                        " since the collapse. But then her newly hopeful world is threatened.\n" +"\n" +
                        "If civilization was lost, what would you preserve? And how far would you go to protect it?"));
        books.add(new Book(14,"The Crow Trap","Ann Cleeves",528,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781529049893.jpg",
                "A cleverly plotted psychological thriller.",
                "Everyone has something to hide . . .\n" +"\n" +
                        "Three very different women come together at an isolated cottage on the North Pennines to complete an environmental survey." +
                        " Three women who each know the meaning of betrayal . . .\n" +"\n" +
                        "Rachael, the team leader, is still reeling after a double betrayal by her lover and boss. Anne, a botanist, sees the survey" +
                        " as a chance to indulge in a little deception of her own. And then there is Grace, a strange, uncommunicative young woman, " +
                        "hiding plenty of her own secrets.\n" +"\n" +
                        "Rachael is the first to arrive at the cottage, but when she gets there she is shocked to discover an apparent suicide. But" +
                        " then another death occurs, and a fourth woman enters the picture – the unconventional Detective Inspector Vera Stanhope, who" +
                        " must piece together the truth from these women’s tangled lives . . ."));
        books.add(new Book(15,"Absolute Power","David Baldacci",576,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447287520.jpg",
                "One of the hottest reads around.",
                "In a heavily guarded mansion in the Virginian countryside, professional burglar and break-in artist, Luther Whitney, " +
                        "is trapped behind a one-way mirror. What he witnesses destroys his faith not only in justice, but all he holds dear.\n" +"\n" +
                        "What follows is an unthinkable abuse of power and a criminal conspiracy, as a breathtaking cover-up is set in motion by those" +
                        " appointed to work for one of the most important people in the world – the President of the United States."));
        books.add(new Book(16,"A Whole Life","Robert Seethaler",160,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781447283904.jpg",
                "Shortlisted for the Booker International Prize.",
                "Andreas lives his whole life in the Austrian Alps, where he arrives as a young boy taken in by a farming family. " +
                        "He is a man of very few words and so, when he falls in love with Marie, he doesn't ask for her hand in marriage, " +
                        "but instead has some of his friends light her name at dusk across the mountain. When Marie dies in an avalanche," +
                        " pregnant with their first child, Andreas' heart is broken. He leaves his valley just once more, to fight in WWII -" +
                        " where he is taken prisoner in the Caucasus – and returns to find that modernity has reached his remote haven . . .\n" +"\n" +
                        "Like John Williams' Stoner or Denis Johnson's Train Dreams, A Whole Life by Robert Seethaler is a tender book about finding" +
                        " dignity and beauty in solitude. An exquisite novel about a simple life, it has already demonstrated its power to move " +
                        "thousands of readers with a message of solace and truth. It looks at the moments, big and small, that make us what we are."));
        books.add(new Book(17,"The Kindness Method","Shahroo Izadi",208,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781509881826.jpg",
                "Map your habits, set your own goals, and treat yourself with the kindness you truly deserve.",
                "Shahroo Izadi has a revolutionary message: treating yourself kindly is the only way to make changes that last." +
                        " She is living proof that her method works – after years of yo-yo dieting she shed over 8 stone (and has kept it off ever since)." +
                        " Professional training coupled with personal experience led her to develop The Kindness Method™, where traditional strict regimes" +
                        " are turned upside down to leave you feeling empowered, positive and ready to embrace change.\n" +
                        "Whether it’s weight loss, cutting down on drinking, improving relationships or ditching a dull job for one that you love," +
                        " The Kindness Method will help you change any unwanted habit. Because when you shift your focus to your individual strengths" +
                        " and skills (rather than what you can’t do), you too will find you have the power to change – for good."));
        books.add(new Book(18,"Solve For Happy","Mo Gawdat",368,
                "https://ik.imagekit.io/panmac/tr:q-75,di-placeholder_portrait_aMjPtD9YZ.jpg,w-350,pr-true,bl/edition/9781509809950.jpg",
                "He explains how even in the face of the unthinkable, happiness is still possible.",
                "In 2001, Mo Gawdat, a remarkable thinker whose gifts had landed him top positions in half a dozen companies and who –" +
                        " in his spare time – had created significant wealth, realized that he was desperately unhappy. A lifelong learner, he " +
                        "attacked the problem as an engineer would, examining all the provable facts and scrupulously following logic. When he was" +
                        " finished, he had discovered the equation for enduring happiness.\n" +"\n" +
                        "Ten years later, that research saved him from despair when his college-aged son, Ali – also intellectually gifted – " +
                        "died during routine surgery. In dealing with the loss, Mo found his mission: he would pull off the type of 'moonshot' that" +
                        " he and his Google [X] colleagues were always aiming for: he would help ten million people become happier by pouring his" +
                        " happiness principles into a book and spreading its message around the world.\n" +"\n" +
                        "One of Solve for Happy's key premises is that happiness is a default state. If we shape expectations to acknowledge the" +
                        " full range of possible events, unhappiness is on its way to being defeated. To steer clear of unhappiness traps, we" +
                        " must dispel the six illusions that cloud our thinking (e.g., the illusion of time, of control, and of fear); overcome the" +
                        " brain's seven deadly defects (e.g., the tendency to exaggerate, label, and filter), and embrace five ultimate truths (e.g., " +
                        "change is real, now is real, unconditional love is real).\n" +"\n" +
                        "By means of several highly original thought experiments, Mo helps readers find enduring contentment by questioning some of " +
                        "the most fundamental aspects of their existence."));



        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();  //  editor.apply();

    }

    public static Utils getInstance(Context context){
        if(instance == null)
            instance = new Utils(context);
        return instance;
    }


    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type );
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type );
        return  books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type );
        return  books;
    }

    public ArrayList<Book> getWishlistBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WISHLIST_BOOKS, null), type );
        return  books;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS, null), type );
        return  books;
    }


    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if(books != null) {
            for (Book b : books) {
                if (b.getId() == id)
                    return b;
            }
        }
        return null;
    }


    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null){
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null){
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWishlist(Book book){
        ArrayList<Book> books = getWishlistBooks();
        if(books != null){
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_BOOKS);
                editor.putString(WISHLIST_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavourites(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if(books != null){
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }


    public boolean removeFromAlreadyRead(int position){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null){
            if (books.remove(books.get(position))) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(int position){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null){
            if (books.remove(books.get(position))) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromWishlist(int position){
        ArrayList<Book> books = getWishlistBooks();
        if(books != null){
            if (books.remove(books.get(position))) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_BOOKS);
                editor.putString(WISHLIST_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromFavourites(int position){
        ArrayList<Book> books = getFavouriteBooks();
        if(books != null){
            if (books.remove(books.get(position))) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean isInWishlistBooks(Book book) {
        ArrayList<Book> books = getWishlistBooks();
        for(Book b:books){
            if(b.getId() == book.getId())
                return true;
        }
        return false;
    }
    public boolean isInAlreadyReadBooks(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        for(Book b:books){
            if(b.getId() == book.getId())
                return true;
        }
        return false;
    }
    public boolean isInCurrentlyReadingBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        for(Book b:books){
            if(b.getId() == book.getId())
                return true;
        }
        return false;
    }
    public boolean isInFavouriteBooks(Book book) {
        ArrayList<Book> books = getFavouriteBooks();
        for(Book b:books){
            if(b.getId() == book.getId())
                return true;
        }
        return false;
    }
}
