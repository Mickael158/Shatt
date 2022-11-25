ServerSocket socketserver;
Socket socketduserveur;
final BufferedReader in;
final PrintWriter out;
final Scanner sc=new Scanner(System.in);
try {
 socketserver = new ServerSocket(5000);
 socketduserveur = socketserver.accept();
 out = new PrintWriter(socketduserveur.getOutputStream());
 in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
 String s;
 s = sc.next();
 out.println(s);
 out.flush(); 
 String message_client ;
 message_client = in.readLine();
 System.out.println("Client : "+message_client);
 }
catch (IOException e) {
 e.printStackTrace();
}