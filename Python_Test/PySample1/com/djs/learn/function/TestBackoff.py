
import backoff

@backoff.on_exception(backoff.expo,
                      Exception,
                      max_tries=3)
def test_exception():
    print("test_exception: running.")
    raise Exception("Wrong value.")

test_exception()
