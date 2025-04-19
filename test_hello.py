def test_output():
    with open("hello.py") as f:
        content = f.read()
    assert "Hello World!" in content
