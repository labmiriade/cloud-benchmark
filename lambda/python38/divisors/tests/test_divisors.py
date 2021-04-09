import json

from divisors import __version__, main


def test_version():
    assert __version__ == '0.1.0'


def test_divisors():
    event = {'pathParameters': {'n': "6"}}
    res = main.handler(event, None)
    assert res['statusCode'] == 200
    body = json.loads(res['body'])
    assert body == [1, 2, 3, 6]
