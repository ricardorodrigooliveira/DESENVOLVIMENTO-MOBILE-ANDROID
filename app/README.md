# SQLITE

	O Android possui um banco de dados integrado denominado SQLite;

	Ele aceita comandos SQL para cria��o de tabelas, consultas, etc... (vide

	_http://www.sqlite.org/lang.html_)

	Contudo, apresenta algumas limita��es:
		* Suporta apenas os tipos de dados TEXT, INTEGER e REAL;
		* N�o oferece suporte � chaves estrangeiras;
		* N�o mant�m a integridade entre os tipos de dados (exemplo: inserir String em campo INTEGER);

	Apesar disso � extremamente leve em termos de consumo de mem�ria;
	
	O SQLite separa instru��es DDL de DML;
	
	As instru��es DDL para a cria��o da estrutura do banco devem ficar em uma  
	classe que estende _SQLiteOpenHelper_;

	Nela, os m�todos _onCreate_ (que cria o banco e suas tabelas) e _onUpdate_  
	(atualiza a estrutura do banco) devem ser implementados;
	
	Al�m disso, a classe _SQLiteOpenHelper_ possui o seguinte construtor:  
	
	_SQLiteOpenHelper(Context P1, String P2, CursorFactory P3, int P4)_  

	Onde:
	* P1 - Contexto associado ao banco (aplica��o);
	* P2 - Nome do banco de dados;
	* P3 - Cursor padronizado para a pesquisa de dados (pode ser null);
	* P4 - Vers�o do banco de dados (todas as vezes que a vers�o �  
		atualizada, o m�todo OnUpdate � executado).  
		
	_public class MeuDb extends SQLiteOpenHelper {_

 		_public MeuDb(Context context) {_  
 			_super(context, "MeuDb", null, 1);_
 		_}_
 		
 		_@Override_  
 		_public void onCreate(SQLiteDatabase db) {_
 			_final String sql = "CREATE TABLE TAB_CLIENTE (COD_CLIENTE INTEGER_
 			_PRIMARY KEY AUTOINCREMENT, NOM_CLIENTE TEXT)";_
 			
 			_db.execSQL(sql);_
 		_}_
 
 		_@Override  
 			_public void onUpgrade(SQLiteDatabase db, int vAntiga, int vNova) {_  
 				_db.execSQL("DROP TABLE IF EXISTS TAB_CLIENTE");_  
 				_onCreate(db);_  
 			_}_
	_}_  
	
	Para executar instru��es DML � necess�rio abrir o banco de dados por meio  
	do m�todo _getWritableDatabase_ que retorna um objeto do tipo  
	_SQLiteDatabase_;  
	
	A classe _SQLiteDatabase_ oferece os m�todos _insert, delete e update_:
	
	_insert (String P1, String P2, ContentValues P3)_, onde:  
	_P1_ � o nome da tabela, _P2_ a forma de tratamento de valores nulos (opcional) e  
	_P3_ os valores a serem inseridos. O insert retorna um long com o ID gerado para o  
	registro inserido;  
	
	update(String P1, ContentValues P2, String P3, String[] P4), onde P1 � o
	nome da tabela, P2 os valores que ser�o atualizados, P3 a cl�usula where e P4
	os par�metros da cl�usula where;  
	
	_delete(String P1, String P2, String[] P3)_, onde _P1_ � o nome da tabela, _P2_ a
	cl�usula where e _P3_ os par�metros da cl�usula where;  
	
	Os valores a serem utilizados nas instru��es insert e update s�o encapsulados em objetos  
	da classe _ContentValues_;  
	A classe ContentValues possui um m�todo put que permite informarmos pares contendo  
	nome da coluna e valor;  
	Exemplo:  
	
		ContentValues cv = new ContentValues();  
		cv.put("NOM_CLIENTE", "JOAO DA SILVA");  
		db.insert("TAB_CLIENTE", null, cv);  
		
		ContentValues cv2 = new ContentValues();  
		cv2.put("NOM_CLIENTE", "BATISTA ANTONIO");  
		// Atualiza o registro onde COD_CLIENTE = 1  
		db.update("TAB_CLIENTE", cv2, "COD_CLIENTE = ?", new String[]{"1"});  
		
		// Apaga o registro onde COD_CLIENTE = 2  
		db.delete("TAB_CLIENTE", "COD_CLIENTE = ?", new String[]{"2"});  
		
	Consultas podem ser realizadas na base de dados por meio do m�todo _query_ da classe  
	_SQLiteDatabase_ que retorna um objeto _Cursor_, utilizado para manipular o conjunto de  
	resultados obtidos;  
	
	_Cursor query (String P1, String[] P2, String P3, String[] P4, String P5, String P6,_
	_String P7)_

	
	Onde:																		 ------------------------------
	_P1_ - nome da tabela;                    |  Cursor c = db.query(        |
	_P2_ - colunas desejadas;                 |  "TAB_CLIENTE",              |
	_P3_ - cl�usula _where_;                    |  new String[]{"NOM_CLIENTE"},|
	_P4_ - par�metros da cl�usula _where_;      |  "COD_CLIENTE = ?",          |
	_P5_ - cl�usula _group by_;                 |  new String[]{"3"},          |
	_P6_ - cl�usula _having_;                   |  null,                       |
	_P7_ - cl�usula _order by_;                 |  null,                       |
  	                                      |  "NOM_CLIENTE");             |
    	                                     ------------------------------
                                         
	A classe Cursor oferece mecanismos de manipula��o dos dados por meio de m�todos:
	
	* _getCount()_ ? total de registros recuperados;
	* _moveToFirst()_ ? posiciona o cursor no primeiro registro do conjunto de dados;
	* _moveToNext()_ ? move para o pr�ximo registro. Retorna false caso n�o existam mais  
		registros no conjunto de dados;  
	* _getInt(int P1), getDouble(int P1), getFloat(int P1), getString(int P1)_ ?  
		retorna o valor de uma coluna informada no par�metro P1 conforme o tipo de dado  
		especificado no m�todo;  
	* _close()_ ? fecha o cursor;  
	
	 --------------------------------------------------------------------
	|Exemplo:                                                            |
	|	c.moveToFirst();                                                   |
	|	Log.i("PersistenciaActivity", "Total: " + c.getCount());           |
	|	do {                                                               |
	|	 Log.i("PersistenciaActivity",                                     |
	|	 "COD: " + c.getInt(0) +                                           |
	|	 " NOME: " + c.getString(1));                                      |
	|	} while (c.moveToNext());                                          |
	|	c.close();                                                         |
	 --------------------------------------------------------------------
		