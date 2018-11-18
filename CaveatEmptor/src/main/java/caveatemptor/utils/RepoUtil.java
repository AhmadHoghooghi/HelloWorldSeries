package caveatemptor.utils;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;

@Repository
@Transactional
public class RepoUtil {

    @PersistenceContext
    private EntityManager em;



    public void runSqlScriptInFile(File file) throws IOException {
        InputStream sqlFileInputStream = new FileInputStream(file);
        BufferedReader sqlFileBufferedReader = new BufferedReader( new InputStreamReader(sqlFileInputStream));
        executeStatements(sqlFileBufferedReader);
    }

    private void executeStatements(BufferedReader br) throws IOException {
        String line;
        while( (line = br.readLine()) != null )
        {
            em.createNativeQuery(line).executeUpdate();
        }
    }
}
