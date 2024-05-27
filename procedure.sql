CREATE PROCEDURE ExcluirTarefaPorID(id INT)
AS
BEGIN
    DELETE FROM tarefa WHERE id = id;
END;
