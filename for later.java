      if (len >= 3){
        File textFile = new File("postal_codes.txt");
        Scanner reader = new Scanner(textFile);
        String stats = reader.nextLine();
        System.out.println(stats);
      }
      else { 
        System.out.println(word);
      }
      reader.close();
    }