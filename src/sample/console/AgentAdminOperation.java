package sample.console;

import com._1c.v8.ibis.admin.AgentAdminException;

/**
 * Administrative operation on a 1C:Enterprise server cluster
 *
 * @param <V> operation result type
 */
public interface AgentAdminOperation<V>
{
    /**
     * Calls the operation
     * 
     * @return operation result
     * @throws AgentAdminException 
     */
    V call();
}
