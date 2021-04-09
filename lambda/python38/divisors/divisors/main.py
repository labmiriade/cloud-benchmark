import json


def handler(event, _):
    n = int(event['pathParameters']['n'])

    def is_divisor(x):
        return n % x == 0

    aux = list(filter(is_divisor, range(1, n+1)))

    return {
        'statusCode': 200,
        'body': json.dumps(aux)
    }
