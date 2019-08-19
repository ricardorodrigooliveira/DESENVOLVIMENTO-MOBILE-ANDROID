# SQLITE

	O Android possui um banco de dados integrado denominado SQLite;

	Ele aceita comandos SQL para criação de tabelas, consultas, etc... (vide

	_http://www.sqlite.org/lang.html_)

	Contudo, apresenta algumas limitações:
		* Suporta apenas os tipos de dados TEXT, INTEGER e REAL;
		* Não oferece suporte à chaves estrangeiras;
		* Não mantém a integridade entre os tipos de dados (exemplo: inserir String em campo INTEGER);

	Apesar disso é extremamente leve em termos de consumo de memória;
	
	O SQLite separa instruções DDL de DML;
	
	As instruções DDL para a criação da estrutura do banco devem ficar em uma  
	classe que estende _SQLiteOpenHelper_;

	Nela, os métodos _onCreate_ (que cria o banco e suas tabelas) e _onUpdate_  
	(atualiza a estrutura do banco) devem ser implementados;
	
	Além disso, a classe _SQLiteOpenHelper_ possui o seguinte construtor:  
	
	_SQLiteOpenHelper(Context P1, String P2, CursorFactory P3, int P4)_  

	Onde:
	* P1 - Contexto associado ao banco (aplicação);
	* P2 - Nome do banco de dados;
	* P3 - Cursor padronizado para a pesquisa de dados (pode ser null);
	* P4 - Versão do banco de dados (todas as vezes que a versão é  
		atualizada, o método OnUpdate é executado).  
		
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
	
	Para executar instruções DML é necessário abrir o banco de dados por meio  
	do método _getWritableDatabase_ que retorna um objeto do tipo  
	_SQLiteDatabase_;  
	
	A classe _SQLiteDatabase_ oferece os métodos _insert, delete e update_:
	
	_insert (String P1, String P2, ContentValues P3)_, onde:  
	_P1_ é o nome da tabela, _P2_ a forma de tratamento de valores nulos (opcional) e  
	_P3_ os valores a serem inseridos. O insert retorna um long com o ID gerado para o  
	registro inserido;  
	
	update(String P1, ContentValues P2, String P3, String[] P4), onde P1 é o
	nome da tabela, P2 os valores que serão atualizados, P3 a cláusula where e P4
	os parâmetros da cláusula where;  
	
	_delete(String P1, String P2, String[] P3)_, onde _P1_ é o nome da tabela, _P2_ a
	cláusula where e _P3_ os parâmetros da cláusula where;  
	
	Os valores a serem utilizados nas instruções insert e update são encapsulados em objetos  
	da classe _ContentValues_;  
	A classe ContentValues possui um método put que permite informarmos pares contendo  
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
		
	Consultas podem ser realizadas na base de dados por meio do método _query_ da classe  
	_SQLiteDatabase_ que retorna um objeto _Cursor_, utilizado para manipular o conjunto de  
	resultados obtidos;  
	
	_Cursor query (String P1, String[] P2, String P3, String[] P4, String P5, String P6,_
	_String P7)_

	
	Onde:																		 ------------------------------
	_P1_ - nome da tabela;                    |  Cursor c = db.query(        |
	_P2_ - colunas desejadas;                 |  "TAB_CLIENTE",              |
	_P3_ - cláusula _where_;                    |  new String[]{"NOM_CLIENTE"},|
	_P4_ - parâmetros da cláusula _where_;      |  "COD_CLIENTE = ?",          |
	_P5_ - cláusula _group by_;                 |  new String[]{"3"},          |
	_P6_ - cláusula _having_;                   |  null,                       |
	_P7_ - cláusula _order by_;                 |  null,                       |
  	                                      |  "NOM_CLIENTE");             |
    	                                     ------------------------------
                                         
	A classe Cursor oferece mecanismos de manipulação dos dados por meio de métodos:
	
	* _getCount()_ ? total de registros recuperados;
	* _moveToFirst()_ ? posiciona o cursor no primeiro registro do conjunto de dados;
	* _moveToNext()_ ? move para o próximo registro. Retorna false caso não existam mais  
		registros no conjunto de dados;  
	* _getInt(int P1), getDouble(int P1), getFloat(int P1), getString(int P1)_ ?  
		retorna o valor de uma coluna informada no parâmetro P1 conforme o tipo de dado  
		especificado no método;  
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
		